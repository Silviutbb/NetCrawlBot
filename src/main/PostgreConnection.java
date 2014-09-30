package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgreConnection {

	private static String url;
	private static String user;
	private static String password;
	private static Connection con;
	private static Statement st;
	private static ResultSet rs;

	private static void testConnected(){
		try {
			if(st.isClosed())
				st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean testConnection() {

		try {
			rs = st.executeQuery("SELECT VERSION()");

			if (rs.next()) {
				NCBUtils.talk("Connecting to database: ");
				NCBUtils.talk(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}

		return true;
	}

	public PostgreConnection() throws Exception {

		url = "jdbc:postgresql://" + Interfata.settings.db_server + "/"
				+ Interfata.settings.db_name;
		user = Interfata.settings.db_user;
		password = Interfata.settings.db_pass;

		con = DriverManager.getConnection(url, user, password);
		st = con.createStatement();

	}

	@SuppressWarnings("static-access")
	public void closeConnection() {
		try {
			this.con.close();
			this.st.close();
			this.rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void vacuum() {
		try {
			testConnected();
			String query = "vacuum full";
			st.executeUpdate(query);
			NCBUtils.talk("vacuumed database");
		} catch (SQLException ex) {
			NCBUtils.talk("Unable to perform vacuum");

		}
	}

	public void sendToDatabase(String[] array, String[] index, String[] table) {

		try {
			testConnected();
			String query;
			for (int i = 0; i < index.length; i++) {
				query = "INSERT INTO " + table[i] + "(ID,data) VALUES('"
						+ index[i] + "', '" + array[i] + "');";
				st.executeUpdate(query);
			}

		} catch (SQLException ex) {
		}
	}

	public void sendToDatabase(String array, String index, String table) {
		try {
			String query = "INSERT INTO " + table + "(ID,data) VALUES('"
					+ index + "', '" + array + "');";
			testConnected();
			st.executeUpdate(query);

		} catch (SQLException ex) {
			NCBUtils.talk("SQL Exception #4 - trying to insert single data");
		}
	}

	public boolean testDatabaseIntegrity() {

		String tables = "";
		NCBUtils.talk("Verifying DB integrity ");

		try {

			String query = "";

			query = "select * from pg_tables where schemaname='public'";
			testConnected();
			rs = st.executeQuery(query);

			while (rs.next()) {
				tables = tables + "\n" + rs.getString(2);
			}

		} catch (SQLException ex) {
			NCBUtils.talk("Error testing the integrity of the database");

		}

		for (int i = 0; i < Interfata.plugins.getNr_of_plugins(); i++)
			if (!NCBUtils.existsInString(tables,
					Interfata.plugins.plugin[i].sql_table)) {
				// NCBUtils.talk("Adding table "+Interfata.plugins.plugin[i].sql_table);
				String query = "";
				if (Interfata.plugins.plugin[i].has_date) {
					query = "CREATE TABLE IF NOT EXISTS "
							+ Interfata.plugins.plugin[i].sql_table + " ("
							+ "  id VARCHAR(64), "
							+ " data varchar(550) NOT NULL, "
							+ "  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  ";
					if (!Interfata.plugins.plugin[i].plugin_is_standard)
						query += " record SERIAL, ";
					query += "PRIMARY KEY (id, date));";
				} else {
					query = "CREATE TABLE IF NOT EXISTS "
							+ Interfata.plugins.plugin[i].sql_table + " ("
							+ "  id varchar(64), "
							+ " data varchar(550) NOT NULL, ";
					if (!Interfata.plugins.plugin[i].plugin_is_standard)
						query += " record SERIAL, ";
					if (Interfata.plugins.plugin[i].sql_table == Interfata.plugins.plugin[0].sql_table)
						query += "parsed boolean DEFAULT FALSE,";
					query += "PRIMARY KEY (id));";
				}

				try {
					testConnected();
					st.executeUpdate(query);
				} catch (SQLException e) {
					NCBUtils.talk("Error testing the integrity of the database");
					return false;
				}
			}

		return true;
	}

	public String[] getCrawlLinksFromDB(int amount) {
		String[] result;
		try{
		result = new String[amount];
		}
		catch(OutOfMemoryError e){
			amount = amount/2;	
			result = new String[amount];
		}
			
		try {

			String query = "";

			query = "select id,data from "
					+ Interfata.plugins.plugin[0].sql_table
					+ " where parsed=FALSE LIMIT " + amount + ";";
			testConnected();
			rs = st.executeQuery(query);
			int i = 0;
			query = "";
			boolean j = true;
			try {
			while (rs.next()) {
				query += "UPDATE " + Interfata.plugins.plugin[0].sql_table
						+ " SET parsed=TRUE " + " WHERE id = '"
						+ rs.getString("id") + "';\n";
				
					result[i] = rs.getString("data");
				
				
				i++;
			}
			} catch (Exception e) {
				
				if (query == "")
					j=false;
			}
			testConnected();
			if(j)
			st.executeUpdate(query);

		} catch (SQLException ex) {
			Logger lgr = Logger.getLogger(PostgreConnection.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		}

		return result;
	}

	public String[] getScanlLinksFromDB(int amount, int id, String table) {
		String[] result = new String[amount];
		int i = 0;
		try {

			String query = "";

			query = "select " + Interfata.plugins.plugin[0].sql_table
					+ ".data from " + Interfata.plugins.plugin[0].sql_table
					+ " INNER JOIN " + table + " ON "
					+ Interfata.plugins.plugin[0].sql_table + ".id=" + table
					+ ".id " + " where " + table + ".record>" + id + " LIMIT "
					+ amount + ";";
			testConnected();
			rs = st.executeQuery(query);

			while (rs.next()) {
				result[i] = rs.getString("data");
				i++;
			}

		} catch (SQLException ex) {
			NCBUtils.talk("SQL Exception #5 - Getting Scan Links!");

		}
		String[] result1 = new String[i];
		for (int j = 0; j < i; j++)
			result1[j] = result[j];

		return result1;
	}

	public boolean isOkToStartCrawling() {
		boolean result = false;
		try {testConnected();
			String query = "";
			query = "select COUNT(id) as nrOfElements from "
					+ Interfata.plugins.plugin[0].sql_table + ";";
			rs = st.executeQuery(query);
			rs.next();
			if (rs.getInt("nrOfElements") >= Interfata.settings.ncb_buffer / 2)
				result = true;
			else
				result = false;

		} catch (SQLException ex) {
			NCBUtils.talk("SQL Exception #6 - Trying to derermine wether if it is ok to start work");
		}

		return result;
	}

	public String getNrOfLinksInDB() {
		String result = "";
		try {testConnected();
			String query = "";
			query = "select count(id) as nrItem from "
					+ Interfata.plugins.plugin[0].sql_table + " where true;";
			rs = st.executeQuery(query);
			query = "";
			rs.next();
			result = rs.getString("nrItem");

		} catch (SQLException ex) {
			NCBUtils.talk("SQL Exception #7 Error getting the number of links in the Database");
			return "0";
		}

		return result;
	}

}
