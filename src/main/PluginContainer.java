package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PluginContainer implements PluginInterface {

	String name; // Name of the plugin
	boolean plugin_is_standard;// If false, it enables active scan for the links
								// seen
	String[] syntax_test_has_in_it_nr;
	int syntax_clean_number;
	String[] syntax_clean_nr;
	String sql_table;
	int syntax_test_hii_number;
	boolean has_date;
	int syntax_is_on_page_number;
	String[] syntax_is_on_page_nr;

	private static boolean toBoolean(String tested) {
		 //NCBUtils.talk("toBoolean a primit '"+tested+"'");
		if (tested.equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	PluginContainer(File file, boolean verbose) throws Exception {
		NCBUtils.talk("Loading plugin...");
		String text = "";
		BufferedReader in;
		boolean sw = true;
		in = new BufferedReader(new FileReader(file));
		String current="";
		while (sw) {
			current = in.readLine();
				//
				text = text + current + "\n";
			if (current==null)
				sw = false;
		}
		in.close();
		// NCBUtils.talk(text);
		StringTokenizer Tok = new StringTokenizer(text);
		while (Tok.hasMoreTokens()) {
			String next = Tok.nextToken();

			Pattern Name = Pattern.compile("name=.*?");
			Matcher mName = Name.matcher(next);
			if (mName.find()) {
				Pattern Name2 = Pattern.compile("name=.*?");
				Matcher mName2 = Name2.matcher(next);
				next = mName2.replaceAll("");
				this.name = next;
			}
			Pattern Plugin_is_standard = Pattern
					.compile("plugin_is_standard=.*?");
			Matcher mPlugin_is_standard = Plugin_is_standard.matcher(next);
			if (mPlugin_is_standard.find()) {
				Pattern Plugin_is_standard2 = Pattern
						.compile("plugin_is_standard=.*?");
				Matcher mPlugin_is_standard2 = Plugin_is_standard2
						.matcher(next);
				next = mPlugin_is_standard2.replaceAll("");
				this.plugin_is_standard = PluginContainer.toBoolean(next);
			}
			// has_date
			Pattern Has_date = Pattern.compile("has_date=.*?");
			Matcher mHas_date = Has_date.matcher(next);
			if (mHas_date.find()) {
				Pattern Has_date2 = Pattern.compile("has_date=.*?");
				Matcher mHas_date2 = Has_date2.matcher(next);
				next = mHas_date2.replaceAll("");
				this.has_date = PluginContainer.toBoolean(next);
			}
			Pattern Sql_table = Pattern.compile("sql_table=.*?");
			Matcher mSql_table = Sql_table.matcher(next);
			if (mSql_table.find()) {
				Pattern Sql_table2 = Pattern.compile("sql\\_table=.*?");
				Matcher mSql_table2 = Sql_table2.matcher(next);
				next = mSql_table2.replaceAll("");
				this.sql_table = next;
			}

			Pattern Syntax_test_hii_number = Pattern
					.compile("syntax_test_hii_number=.*?");
			Matcher mSyntax_test_hii_number = Syntax_test_hii_number
					.matcher(next);
			// NCBUtils.talk(next);
			if (mSyntax_test_hii_number.find()) {
				Pattern Syntax_test_hii_number2 = Pattern
						.compile("syntax_test_hii_number=.*?");
				Matcher mSyntax_test_hii_number2 = Syntax_test_hii_number2
						.matcher(next);
				// NCBUtils.talk("rec0"+next);
				next = mSyntax_test_hii_number2.replaceAll("");
				this.syntax_test_hii_number = Integer.parseInt(next);
			}
			Pattern Syntax_clean_number = Pattern
					.compile("syntax_clean_number=.*?");
			Matcher mSyntax_clean_number = Syntax_clean_number.matcher(next);
			if (mSyntax_clean_number.find()) {
				Pattern Syntax_clean_number2 = Pattern
						.compile("syntax_clean_number=.*?");
				Matcher mSyntax_clean_number2 = Syntax_clean_number2
						.matcher(next);
				next = mSyntax_clean_number2.replaceAll("");
				this.syntax_clean_number = Integer.parseInt(next);
			}
			this.syntax_test_has_in_it_nr = new String[this.syntax_test_hii_number];
			this.syntax_clean_nr = new String[this.syntax_clean_number];

		}

		StringTokenizer Tok2 = new StringTokenizer(text);
		while (Tok2.hasMoreTokens()) {
			String next = Tok2.nextToken();
			for (int i = 1; i <= this.syntax_clean_number; i++) {
				Pattern Syntax_clean_nr = Pattern.compile("syntax_clean_nr" + i
						+ "=.*?");
				Matcher mSyntax_clean_nr = Syntax_clean_nr.matcher(next);
				if (mSyntax_clean_nr.find()) {
					Pattern Syntax_clean_nr2 = Pattern
							.compile("syntax_clean_nr" + i + "=.*?");
					Matcher mSyntax_clean_nr2 = Syntax_clean_nr2.matcher(next);
					next = mSyntax_clean_nr2.replaceAll("");
					this.syntax_clean_nr[i - 1] = next;
				}
			}
			for (int i = 1; i <= this.syntax_test_hii_number; i++) {
				Pattern Syntax_test_has_in_it_nr = Pattern
						.compile("syntax_test_has_in_it_nr" + i + "=.*?");
				Matcher mSyntax_test_has_in_it_nr = Syntax_test_has_in_it_nr
						.matcher(next);
				if (mSyntax_test_has_in_it_nr.find()) {
					Pattern Syntax_test_has_in_it_nr2 = Pattern
							.compile("syntax_test_has_in_it_nr" + i + "=.*?");
					// NCBUtils.talk("syntax_test_has_in_it_nr" + i +
					// "=.*?");
					Matcher mSyntax_test_has_in_it_nr2 = Syntax_test_has_in_it_nr2
							.matcher(next);
					// NCBUtils.talk(next);
					next = mSyntax_test_has_in_it_nr2.replaceAll("");
					// NCBUtils.talk(next);
					this.syntax_test_has_in_it_nr[i - 1] = next;
					// NCBUtils.talk(this.syntax_test_has_in_it_nr[i - 1]);
				}
			}
			
			Pattern Syntax_has_on_page = Pattern
					.compile("syntax_is_on_page_number=.*?");
			Matcher mSyntax_has_on_page = Syntax_has_on_page
					.matcher(next);
			// NCBUtils.talk(next);
			if (mSyntax_has_on_page.find()) {
				Pattern Syntax_has_on_page2 = Pattern
						.compile("syntax_is_on_page_number=.*?");
				Matcher mSyntax_has_on_page2 = Syntax_has_on_page2
						.matcher(next);
				// NCBUtils.talk("rec0"+next);
				next = mSyntax_has_on_page2.replaceAll("");
				this.syntax_is_on_page_number = Integer.parseInt(next);}
			
			for (int i = 1; i <= this.syntax_is_on_page_number; i++) {
				Pattern syntax_is_on_page_nr = Pattern
						.compile("syntax_is_on_page_nr" + i + "=.*?");
				Matcher msyntax_is_on_page_nr = syntax_is_on_page_nr
						.matcher(next);
				if (msyntax_is_on_page_nr.find()) {
					Pattern syntax_is_on_page_nr2 = Pattern
						.compile("syntax_is_on_page_nr" + i + "=.*?");
					// NCBUtils.talk("syntax_test_has_in_it_nr" + i +
					// "=.*?");
					Matcher msyntax_is_on_page_nr2 = syntax_is_on_page_nr2
							.matcher(next);
					// NCBUtils.talk(next);
					next = msyntax_is_on_page_nr2.replaceAll("");
					// NCBUtils.talk(next);
					this.syntax_is_on_page_nr[i - 1] = next;
					// NCBUtils.talk(this.syntax_test_has_in_it_nr[i - 1]);
				}
			
		}

		}
		if (verbose) {
			NCBUtils.talk("Name: " + this.name);
			NCBUtils.talk("Is standard: " + this.plugin_is_standard);
			NCBUtils.talk("Sql_name: " + this.sql_table);
			NCBUtils.talk("Has_date: " + this.has_date);
			NCBUtils.talk("Syntax_clean_number: "
					+ this.syntax_clean_number);
			for (int i = 1; i <= this.syntax_clean_number; i++) {
				NCBUtils.talk("Syntax_clean_nr" + i + ": "
						+ this.syntax_clean_nr[i - 1]);
			}
			NCBUtils.talk("Syntax_test_hii_number: "
					+ this.syntax_test_hii_number);
			for (int i = 1; i <= this.syntax_test_hii_number; i++) {
				NCBUtils.talk("Syntax_test_has_in_it_nr" + i + ": "
						+ this.syntax_test_has_in_it_nr[i - 1]);
			}
			NCBUtils.talk("Syntax_is_on_page_number: "
					+ this.syntax_is_on_page_number);
			for (int i = 1; i <= this.syntax_is_on_page_number; i++) {
				NCBUtils.talk("Syntax_is_on_page_nr" + i + ": "
						+ this.syntax_is_on_page_nr[i - 1]);
			}
		}

	}

	public PluginContainer() {
		super();
	}
}
