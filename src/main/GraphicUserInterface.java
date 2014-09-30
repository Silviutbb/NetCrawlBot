package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Toolkit;

public class GraphicUserInterface {
	JFrame window = new JFrame();
	JScrollPane scrollPane;
	JTextArea txtrConsole;
	JTextPane nrOfLinks;
	JTextPane txtpnCrawlingIsOff;
	JButton btnStartCrawling;
	 JTextPane textPane;
	 JTextPane txtpnScanningIsOff;
	 JButton btnStartScanning;

	@SuppressWarnings("deprecation")
	public GraphicUserInterface() {
		window.setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						GraphicUserInterface.class
								.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		window.setTitle("Net Crawl Bot SCS");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JPanel panel = new JPanel();
		window.getContentPane().add(panel, BorderLayout.CENTER);

		scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		txtrConsole = new JTextArea();
		txtrConsole.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scrollPane.setViewportView(txtrConsole);
		txtrConsole.setLineWrap(true);
		window.setSize(500, 259);

		btnStartCrawling = new JButton("Start Crawling");
		btnStartCrawling.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if (btnStartCrawling.getText() == "Start Crawling")
					NCBUtils.startCrawling();
				else
					NCBUtils.stopCrawling();
			}
		});

		btnStartScanning = new JButton("Start Scanning");
		btnStartScanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnStartScanning.getText() == "Start Scanning")
					NCBUtils.startScanning();
				else
					NCBUtils.stopScanning();
			}
		});

		txtpnCrawlingIsOff = new JTextPane();
		txtpnCrawlingIsOff.setBackground(Color.RED);
		txtpnCrawlingIsOff.setEditable(false);
		txtpnCrawlingIsOff.setText("Crawling is OFF");

		txtpnScanningIsOff = new JTextPane();
		txtpnScanningIsOff.setBackground(Color.RED);
		txtpnScanningIsOff.setEditable(false);
		txtpnScanningIsOff.setText("Scanning is off");

		JTextPane txtpnLinksInDatabase = new JTextPane();
		txtpnLinksInDatabase.setEditable(false);
		txtpnLinksInDatabase.setBackground(SystemColor.menu);
		txtpnLinksInDatabase.setText("Links in database:");

		JTextPane txtpnTimePerPage = new JTextPane();
		txtpnTimePerPage.setEditable(false);
		txtpnTimePerPage.setBackground(SystemColor.menu);
		txtpnTimePerPage.setText("Time per page:");

		nrOfLinks = new JTextPane();

		nrOfLinks.setBackground(SystemColor.menu);
		nrOfLinks.setEditable(false);
		nrOfLinks.setText("Loading...");

		textPane = new JTextPane();
		textPane.setBackground(SystemColor.menu);
		textPane.setEditable(false);
		textPane.setText("123");

		JTextPane txtpnS = new JTextPane();
		txtpnS.setBackground(SystemColor.menu);
		txtpnS.setEditable(false);
		txtpnS.setText("s");

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addContainerGap(12, Short.MAX_VALUE)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.TRAILING, false)
												.addComponent(btnStartScanning,
														0, 0, Short.MAX_VALUE)
												.addComponent(
														btnStartCrawling,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addGap(18)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING, false)
												.addComponent(
														txtpnScanningIsOff)
												.addComponent(
														txtpnCrawlingIsOff))
								.addGap(46)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING, false)
												.addComponent(
														txtpnTimePerPage,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														txtpnLinksInDatabase,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addComponent(
														nrOfLinks,
														GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addComponent(
																		textPane,
																		GroupLayout.PREFERRED_SIZE,
																		36,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		ComponentPlacement.RELATED)
																.addComponent(
																		txtpnS,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(55, Short.MAX_VALUE))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 484,
						Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addContainerGap()
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtpnCrawlingIsOff,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnStartCrawling)
										.addComponent(txtpnLinksInDatabase,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(nrOfLinks,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtpnTimePerPage,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(txtpnS,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(textPane,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnStartScanning)
										.addComponent(txtpnScanningIsOff,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE,
								119, Short.MAX_VALUE)));

		panel.setLayout(gl_panel);

		JMenuBar menuBar = new JMenuBar();
		window.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				Interfata.database.closeConnection();
				System.exit(-1);
			}
		});

		JMenuItem mntmStartCrawl = new JMenuItem("Start Crawwling");
		mnFile.add(mntmStartCrawl);

		JMenuItem mntmStartScanning = new JMenuItem("Start Scanning");
		mnFile.add(mntmStartScanning);

		JMenuItem mntmPreferences = new JMenuItem("Preferences");
		mntmPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String OS = System.getProperty("os.name").toLowerCase();
				if (OS.indexOf("win") >= 0)
					try {
						Runtime.getRuntime().exec("notepad crawl.settings");
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				if (OS.indexOf("mac") >= 0)
					try {
						Runtime.getRuntime().exec("open crawl.settings");
					} catch (IOException e1) {

						e1.printStackTrace();
					}
				if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0
						|| OS.indexOf("aix") >= 0)
					try {
						Runtime.getRuntime().exec("edit crawl.settings");
					} catch (IOException e1) {

						e1.printStackTrace();
					}

			}
		});

		JMenuItem mntmAddSeed = new JMenuItem("Add seed");
		mntmAddSeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String adress = GUIUtils.getSeed();
				String domain = NCBUtils.getDomain(adress);
				String content = "";
				try {
					URL address = new URL("http://" + adress);

					InputStreamReader pageInput = new InputStreamReader(address
							.openStream());
					BufferedReader source = new BufferedReader(pageInput);
					String sourceLine;

					while ((sourceLine = source.readLine()) != null)
						content += sourceLine + "/n";
				} catch (IOException ex) {

					ex.printStackTrace();
					NCBUtils.talk("Error while getting web content #1");
				}

				String next;
				int i, j;
				boolean inMatch, offMatch;

				for (i = 0; i < Interfata.plugins.getNr_of_plugins()
						&& Interfata.plugins.plugin[i].sql_table == "links"; i++) {
					offMatch = false;

					StringTokenizer Tok = new StringTokenizer(content);
					while (Tok.hasMoreTokens()) {
						next = Tok.nextToken();

						if (Interfata.plugins.plugin[i].syntax_is_on_page_number == 0)
							offMatch = true;
						for (i = 0; i < Interfata.plugins.plugin[i].syntax_is_on_page_number; i++)
							if (NCBUtils
									.existsInString(
											next,
											Interfata.plugins.plugin[i].syntax_is_on_page_nr[i]))
								offMatch = true;

						inMatch = false;

						if (Interfata.plugins.plugin[i].syntax_is_on_page_number == 0)
							offMatch = true;
						// in token conditions
						for (j = 0; j < Interfata.plugins.plugin[i].syntax_test_hii_number; j++)
							// searching stage
							if (NCBUtils
									.existsInString(
											next,
											Interfata.plugins.plugin[i].syntax_test_has_in_it_nr[j]))
								inMatch = true;
						for (j = 0; j < Interfata.plugins.plugin[i].syntax_clean_number
								&& inMatch && offMatch; j++)
							// cleaning stage
							next = NCBUtils
									.remover(
											next,
											Interfata.plugins.plugin[i].syntax_clean_nr[j]);

						if (inMatch && offMatch && (next != ""))

							try {

								if (Interfata.plugins.plugin[i].sql_table != "links")
									Interfata.buffers.crawlBuffer
											.insert(next,
													Interfata.plugins.plugin[i].sql_table,
													adress);
								else {
									if (NCBUtils.existsInString(next, domain)) {
										Interfata.buffers.crawlBuffer
												.insert(next,
														Interfata.plugins.plugin[i].sql_table,
														next);
									} else {
										if (NCBUtils
												.existsInString(next, "www")
												|| NCBUtils.existsInString(
														next, "www2") || NCBUtils.existsInString(next, "http.*/"))
											Interfata.buffers.crawlBuffer
													.insert(next,
															Interfata.plugins.plugin[i].sql_table,
															next);
										else
											Interfata.buffers.crawlBuffer
													.insert(domain + "/" + next,
															Interfata.plugins.plugin[i].sql_table,
															domain + next);
									}
								}
							} catch (Exception ex) {

								NCBUtils.talk("Failed to ad into crawl Buffer #1");
							}
					}

				}
				Interfata.buffers.crawlBuffer.flushBuffer();

			}

		});
		mnFile.add(mntmAddSeed);
		mnFile.add(mntmPreferences);

		JMenuItem mntmOpenViewer = new JMenuItem("Open viewer");
		mnFile.add(mntmOpenViewer);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		mnFile.add(mntmExit);

		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HelpPF hp = new HelpPF();
			}
		});
		mnAbout.add(mntmHelp);

		window.show();
	}
}
