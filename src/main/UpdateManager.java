package main;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UpdateManager {
	private static URL home;
	private int version;

	private static int patternMatcherComboInt(String next, String syntax) {
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

	private static String patternMatcherCombo(String next, String syntax) {
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

	private static boolean existsInString(String input, String matched) {
		Pattern pat1 = Pattern.compile(matched);
		Matcher mPat1 = pat1.matcher(input);
		return mPat1.find();
	}

	public UpdateManager(String adress, int version) {
		this.version = version;
		try {
			home = new URL("http://silviu.condruz.com/ncbversion.txt");
			InputStreamReader pageInput = new InputStreamReader(
					home.openStream());
			BufferedReader source = new BufferedReader(pageInput);
			String sourceLine;
			String content = "";
			while ((sourceLine = source.readLine()) != null)
				content += sourceLine + "\n";
			StringTokenizer tok = new StringTokenizer(content, "\n");
			String next = "";
			String comment = "";
			int newVersion = 0;
			while (tok.hasMoreTokens()) {
				next = tok.nextToken();
				if (existsInString(next, "version="))
					newVersion = patternMatcherComboInt(next, "version=");
				if (existsInString(next, "comment="))
					comment = patternMatcherCombo(next, "comment=");
			}
			if (this.version < newVersion) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException | UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog(null,
						"Your current version is not up to date!\n" + comment+ "\nDo you want to go to the website for the latest download? ",
						"New version found!", dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					URI uri = new URL("http://silviu.condruz.com").toURI();
					Desktop desktop = Desktop.isDesktopSupported() ? Desktop
							.getDesktop() : null;
					if (desktop != null
							&& desktop.isSupported(Desktop.Action.BROWSE)) {
						try {
							desktop.browse(uri);
							System.exit(-1);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}

		} catch (Exception e) {
			System.out.println("Unable to reach update server!");
		}

	}
}
