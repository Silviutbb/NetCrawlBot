package main;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class HelpPF {
	JFrame frmHelp;
	
	public HelpPF() {
		frmHelp = new JFrame();
		frmHelp.setTitle("Help");
		frmHelp.setSize(655,195);
		
		JTextPane txtpnHelp = new JTextPane();
		txtpnHelp.setBackground(SystemColor.menu);
		txtpnHelp.setFont(new Font("Times New Roman", Font.BOLD, 24));
		txtpnHelp.setEditable(false);
		txtpnHelp.setText("Help");
		
		JTextPane txtpnForHelpUsing = new JTextPane();
		txtpnForHelpUsing.setBackground(SystemColor.menu);
		txtpnForHelpUsing.setEditable(false);
		txtpnForHelpUsing.setText("For Help using this program you should read the documentation that comes with it.\r\n\r\n");
		
		JTextPane txtpnForHelpUsing_1 = new JTextPane();
		txtpnForHelpUsing_1.setBackground(SystemColor.menu);
		txtpnForHelpUsing_1.setEditable(false);
		txtpnForHelpUsing_1.setText("For help using Regular Expressions follow this link  :");
		
		JTextPane txtpnWww = new JTextPane();
		txtpnWww.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtpnWww.setForeground(SystemColor.textHighlight);
		txtpnWww.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				URI uri;
				try {
					uri = new URL("http://www.regular-expressions.info/").toURI();
				
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop
						.getDesktop() : null;
				if (desktop != null
						&& desktop.isSupported(Desktop.Action.BROWSE)) {
					
						desktop.browse(uri);
					}
				}
				 catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		txtpnWww.setBackground(SystemColor.menu);
		txtpnWww.setEditable(false);
		txtpnWww.setText("http://www.regular-expressions.info/");
		GroupLayout groupLayout = new GroupLayout(frmHelp.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(49)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtpnForHelpUsing_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtpnWww, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtpnForHelpUsing, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(222)
							.addComponent(txtpnHelp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(53, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnHelp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtpnForHelpUsing, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtpnForHelpUsing_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtpnWww, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(122, Short.MAX_VALUE))
		);
		frmHelp.getContentPane().setLayout(groupLayout);
		this.frmHelp.show();
	}
}
