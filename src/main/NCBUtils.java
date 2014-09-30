package main;

import java.awt.Color;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NCBUtils {

	public static void talk(String s) {
		Interfata.gr.txtrConsole.setText(Interfata.gr.txtrConsole.getText()
				+ "\n" + s);
	}
	


	public static String getHash(String stringToEncrypt) {

		MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(stringToEncrypt.getBytes());
			String encryptedString = new BigInteger(1, messageDigest.digest())
					.toString(16);
			return encryptedString;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			NCBUtils.talk("Hash function died!");
			e.printStackTrace();
			return "Hash function died";
		}
	}

	public static String addDomain(String domain, String link) {
		Pattern www1 = Pattern.compile("http:");
		Matcher wwwM1 = www1.matcher(link);
		if (!wwwM1.find()) {
			link = domain + "/" + link;

		}
		return link;
	}

	public static String getDomain(String domain) {
		try {
			char[] sir = domain.toCharArray();
			char[] sir2 = new char[domain.length()];
			int dimSir2 = 0;
			for (int i = 0; i < sir.length; i++) {
				if (sir[i] != '/') {
					sir2[dimSir2] = sir[i];
					dimSir2++;
				} else
					i = sir.length + 1;
			}
			domain = "";
			for (int i = 0; i < dimSir2; i++) {
				domain = domain + sir2[i];
			}
		} catch (Exception e) {
			domain="";
		}
		
		return  domain;
	}

	public static int patternMatcherComboInt(String next, String syntax) {
		Pattern pat1 = Pattern.compile(syntax);
		Matcher mPat1 = pat1.matcher(next);
		if (mPat1.find()) {
			Pattern pat2 = Pattern.compile(syntax);
			Matcher mPat2 = pat2.matcher(next);
			next = mPat2.replaceAll("");
			return Integer.parseInt(next);
		}
		return -1;
	}

	public static String patternMatcherCombo(String next, String syntax) {
		Pattern pat1 = Pattern.compile(syntax);
		Matcher mPat1 = pat1.matcher(next);
		if (mPat1.find()) {
			Pattern pat2 = Pattern.compile(syntax);
			Matcher mPat2 = pat2.matcher(next);
			next = mPat2.replaceAll("");
			return next;
		}
		return "";
	}

	public static String patternReplace(String next, String syntax, String with) {
		Pattern pat1 = Pattern.compile(syntax);
		Matcher mPat1 = pat1.matcher(next);
		if (mPat1.find()) {
			Pattern pat2 = Pattern.compile(syntax);
			Matcher mPat2 = pat2.matcher(next);
			next = mPat2.replaceAll(with);
			
		}
		return next;
	}

	public static boolean existsInString(String input, String matched) {
		// System.out.println("perechea " + input +"  "+matched);
		Pattern pat1 = Pattern.compile(matched);
		Matcher mPat1 = pat1.matcher(input);
		return mPat1.find();
	}

	public static String remover(String content, String syntax) {
		Pattern remove = Pattern.compile(syntax);
		Matcher mremove = remove.matcher(content);
		while (mremove.find())
			content = mremove.replaceAll("");
		return content;
	}

	public static void startCrawling() {

			Interfata.masterWorker.startCrawlers();
			Interfata.gr.txtpnCrawlingIsOff.setText("Crawling is ON");
			Interfata.gr.txtpnCrawlingIsOff.setBackground(Color.GREEN);
			Interfata.gr.btnStartCrawling.setText("Stop Crawling");
		
		
	}
	



	public static void stopCrawling() {
		Interfata.masterWorker.stopCrawlers();
		Interfata.gr.txtpnCrawlingIsOff.setText("Crawling is OFF");
		Interfata.gr.txtpnCrawlingIsOff.setBackground(Color.RED);
		Interfata.gr.btnStartCrawling.setText("Start Crawling");
	}
	
	public static void startScanning() {		
		Interfata.gr.txtpnScanningIsOff.setText("Scanning is ON");
		Interfata.gr.txtpnScanningIsOff.setBackground(Color.GREEN);
		Interfata.gr.btnStartScanning.setText("Stop Scanning");
		Interfata.masterWorker.startScanners();
	
	
}




public static void stopScanning() {
	
	Interfata.gr.txtpnScanningIsOff.setText("Scanning is OFF");
	Interfata.gr.txtpnScanningIsOff.setBackground(Color.RED);
	Interfata.gr.btnStartScanning.setText("Start Scanning");
	Interfata.masterWorker.stopScanners();
}

}
