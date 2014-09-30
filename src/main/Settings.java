package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Settings {
	int ncb_crawl_threads;
	String db_server;
	String db_name;
	String db_user;
	String db_pass;
	int ncb_buffer;
	int ncb_scan_threads;

	Settings(boolean verbose) throws Exception {
		NCBUtils.talk("Loading settings...");
		String text = "";
		BufferedReader in;
		boolean sw = true;
		
			in = new BufferedReader(new FileReader("crawl.settings"));
			while (sw) {
				String current = in.readLine();
				if (!current.startsWith("#"))
					text = text + current + "\n";
				if (current.isEmpty())
					sw = false;
			}
			in.close();
		
		StringTokenizer Tok = new StringTokenizer(text);
		while (Tok.hasMoreTokens()) {
			String next = Tok.nextToken();

			Pattern threads = Pattern.compile("ncb_crawl_threads=.*?");
			Matcher mthreads = threads.matcher(next);
			if (mthreads.find()) {
				Pattern threads2 = Pattern.compile("ncb_crawl_threads=.*?");
				Matcher mthreads2 = threads2.matcher(next);
				next = mthreads2.replaceAll("");
				this.ncb_crawl_threads = Integer.parseInt(next);
			}
			Pattern threads21 = Pattern.compile("ncb_scan_threads=.*?");
			Matcher mthreads21 = threads21.matcher(next);
			if (mthreads21.find()) {
				Pattern threads22 = Pattern.compile("ncb_scan_threads=.*?");
				Matcher mthreads22 = threads22.matcher(next);
				next = mthreads22.replaceAll("");
				this.ncb_scan_threads = Integer.parseInt(next);
			}
			Pattern pBuffer = Pattern.compile("ncb_buffer=.*?");
			Matcher mPBuffer = pBuffer.matcher(next);
			if (mPBuffer.find()) {
				Pattern pBuffer2 = Pattern.compile("ncb_buffer=.*?");
				Matcher mPBuffer2 = pBuffer2.matcher(next);
				next = mPBuffer2.replaceAll("");
				this.ncb_buffer = Integer.parseInt(next);
			}
			Pattern pServ = Pattern.compile("db_server=.*?");
			Matcher mPServ = pServ.matcher(next);
			if (mPServ.find()) {
				Pattern pServ2 = Pattern.compile("db_server=.*?");
				Matcher mPServ2 = pServ2.matcher(next);
				next = mPServ2.replaceAll("");
				this.db_server = next;
			}

			Pattern pUser = Pattern.compile("db_user=.*?");
			Matcher mPUser = pUser.matcher(next);
			if (mPUser.find()) {
				Pattern pUser2 = Pattern.compile("db_user=.*?");
				Matcher mPUser2 = pUser2.matcher(next);
				next = mPUser2.replaceAll("");
				this.db_user = next;
			}
			Pattern pPass = Pattern.compile("db_pass=.*?");
			Matcher mPPass = pPass.matcher(next);
			if (mPPass.find()) {
				Pattern pPass2 = Pattern.compile("db_pass=.*?");
				Matcher mPPass2 = pPass2.matcher(next);
				next = mPPass2.replaceAll("");
				this.db_pass = next;
			}
			Pattern pName = Pattern.compile("db_name=.*?");
			Matcher mPName = pName.matcher(next);
			if (mPName.find()) {
				Pattern pName2 = Pattern.compile("db_name=.*?");
				Matcher mPName2 = pName2.matcher(next);
				next = mPName2.replaceAll("");
				this.db_name = next;
			}
		}
		if(verbose){
		NCBUtils.talk("Threads crawl: " + this.ncb_crawl_threads +"\nScan threads: " + this.ncb_scan_threads + "\nBuffer: "
				+ this.ncb_buffer + " units \nDatabase server: " + this.db_server);
		NCBUtils.talk("Database password: "+this.db_pass);
		NCBUtils.talk("Database user: "+this.db_user);
		NCBUtils.talk("Database : "+this.db_name);
		NCBUtils.talk("Settings loaded!");
		}
	}
}
