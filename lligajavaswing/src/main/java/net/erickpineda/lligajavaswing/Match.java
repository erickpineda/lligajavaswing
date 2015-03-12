package net.erickpineda.lligajavaswing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class Match extends JPanel {
	private JTextField goalsLocal;
	private JTextField goalsVisitor;

	/**
	 * Create the panel.
	 */
	public Match() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 79, 146, 35, 86, 0 };
		gbl_panel.rowHeights = new int[] { 66, 20, 35, 20, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		
		JLabel labelMessages = new JLabel("Enter an outcome");
		labelMessages.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_labelMessages = new GridBagConstraints();
		gbc_labelMessages.insets = new Insets(0, 0, 5, 0);
		gbc_labelMessages.gridwidth = 3;
		gbc_labelMessages.gridx = 1;
		gbc_labelMessages.gridy = 0;
		panel.add(labelMessages, gbc_labelMessages);

		JComboBox clubHome = new JComboBox();
		GridBagConstraints gbc_clubHome = new GridBagConstraints();
		gbc_clubHome.anchor = GridBagConstraints.NORTH;
		gbc_clubHome.fill = GridBagConstraints.HORIZONTAL;
		gbc_clubHome.insets = new Insets(0, 0, 5, 5);
		gbc_clubHome.gridx = 1;
		gbc_clubHome.gridy = 1;
		panel.add(clubHome, gbc_clubHome);

		goalsLocal = new JTextField();
		goalsLocal.setColumns(10);
		GridBagConstraints gbc_goalsLocal = new GridBagConstraints();
		gbc_goalsLocal.anchor = GridBagConstraints.NORTHWEST;
		gbc_goalsLocal.insets = new Insets(0, 0, 5, 0);
		gbc_goalsLocal.gridx = 3;
		gbc_goalsLocal.gridy = 1;
		panel.add(goalsLocal, gbc_goalsLocal);

		JComboBox clubAway = new JComboBox();
		GridBagConstraints gbc_clubAway = new GridBagConstraints();
		gbc_clubAway.anchor = GridBagConstraints.NORTH;
		gbc_clubAway.fill = GridBagConstraints.HORIZONTAL;
		gbc_clubAway.insets = new Insets(0, 0, 5, 5);
		gbc_clubAway.gridx = 1;
		gbc_clubAway.gridy = 3;
		panel.add(clubAway, gbc_clubAway);

		goalsVisitor = new JTextField();
		goalsVisitor.setColumns(10);
		GridBagConstraints gbc_goalsVisitor = new GridBagConstraints();
		gbc_goalsVisitor.insets = new Insets(0, 0, 5, 0);
		gbc_goalsVisitor.anchor = GridBagConstraints.NORTHWEST;
		gbc_goalsVisitor.gridx = 3;
		gbc_goalsVisitor.gridy = 3;
		panel.add(goalsVisitor, gbc_goalsVisitor);

		JPanel paneButto = new JPanel();
		FlowLayout flowLayout = (FlowLayout) paneButto.getLayout();
		flowLayout.setVgap(10);
		add(paneButto, BorderLayout.SOUTH);

		JButton createMatchButto = new JButton("Create Match");
		paneButto.add(createMatchButto);

		JButton exitButto = new JButton("Exit");
		paneButto.add(exitButto);

	}
}
