package net.erickpineda.lligajavaswing;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class JPanelMatch extends JPanel {
	/**
	 * Id de la clase JPanelMatch.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Lista de clubs.
	 */
	private List<Club> clubs;
	/**
	 * Campo de texto para los goles de local.
	 */
	private JTextField goalsLocal;
	/**
	 * Campo de texto para los goles de visitante.
	 */
	private JTextField goalsVisitor;

	/**
	 * Create the panel.
	 */
	public JPanelMatch(List<Club> teams, final JDialog owner) {
		setLayout(new BorderLayout(0, 0));
		clubs = teams;

		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 79, 146, 35, 86, 0 };
		gbl_panel.rowHeights = new int[] { 66, 20, 35, 20, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel labelMessages = new JLabel("Enter an outcome");
		labelMessages.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_labelMessages = new GridBagConstraints();
		gbc_labelMessages.insets = new Insets(0, 0, 5, 0);
		gbc_labelMessages.gridwidth = 3;
		gbc_labelMessages.gridx = 1;
		gbc_labelMessages.gridy = 0;
		panel.add(labelMessages, gbc_labelMessages);

		final JComboBox<String> clubHome = new JComboBox<String>();
		showClubs(clubHome);

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

		final JComboBox<String> clubAway = new JComboBox<String>();
		showClubs(clubAway);

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
		createMatchButto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMatchResult(clubHome, clubAway);
			}
		});
		paneButto.add(createMatchButto);

		JButton exitButto = new JButton("Exit");
		exitButto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				owner.setVisible(false);
			}
		});
		paneButto.add(exitButto);

	}

	/**
	 * 
	 * @return Retorna un JscrollPane a partir de una liga creada.
	 */
	public JScrollPane createLeague() {
		League ligaNueva = new League(clubs);
		return ligaNueva.showTable();
	}

	/**
	 * Método que se ejecuta, cuando se crean partidos entre clubs. Un Club no
	 * puede jugar contra si mismo.
	 * 
	 * @param clubHome
	 *            Seráel club de casa.
	 * @param clubAway
	 *            Será el club visitante.
	 */
	public void createMatchResult(JComboBox<String> clubHome,
			JComboBox<String> clubAway) {

		if (validateInteger(goalsLocal.getText()) == true
				&& validateInteger(goalsVisitor.getText()) == true) {

			String cLocal = (String) clubHome.getSelectedItem();
			String cVisitor = (String) clubAway.getSelectedItem();

			if (!cLocal.equals(cVisitor)) {
				int golLocal = toInteger(goalsLocal.getText());
				int golVisitor = toInteger(goalsVisitor.getText());

				makeCalculations(cLocal, golLocal, cVisitor, golVisitor);

			} else {
				invalidClubs();
			}

		} else {
			nullValues();
		}
	}

	/**
	 * 
	 * @return Retorna la lista de clubs.
	 */
	public List<Club> getClubs() {
		return clubs;
	}

	/**
	 * Mensage de error para cuando se intenta crear un partido contra un mismo
	 * club.
	 */
	public void invalidClubs() {
		JOptionPane.showMessageDialog(null,
				"A club can not play against itself");
	}

	/**
	 * Método que carga con todos los cálculos del partido, siendo victoria
	 * empates, derrotas y los puntos sumados después del encuentro.
	 * 
	 * @param cLocal
	 *            Nombre del club local.
	 * @param golLocal
	 *            Goles del club local.
	 * @param cVisitor
	 *            Nombre del club visitante.
	 * @param golVisitor
	 *            Goles del club visitante.
	 */
	public void makeCalculations(String cLocal, int golLocal, String cVisitor,
			int golVisitor) {

		for (Club c : clubs) {
			if (golLocal > golVisitor) {
				if (c.getClubName().equals(cLocal)) {
					c.addVictory();
					c.addPoints();
					System.out.println(c);
				}
				if (c.getClubName().equals(cVisitor)) {
					c.addDefeat();
					c.addPoints();
					System.out.println(c);
				}
			} else if (golLocal == golVisitor) {
				if (c.getClubName().equals(cLocal)) {
					c.addDrawn();
					c.addPoints();
					System.out.println(c);
				}
				if (c.getClubName().equals(cVisitor)) {
					c.addDrawn();
					c.addPoints();
					System.out.println(c);
				}
			} else {
				if (c.getClubName().equals(cLocal)) {
					c.addDefeat();
					c.addPoints();
					System.out.println(c);
				}
				if (c.getClubName().equals(cVisitor)) {
					c.addVictory();
					c.addPoints();
					System.out.println(c);
				}
			}
		}
	}

	/**
	 * Mensaje de error, cuando se ingresan valores nulos.
	 */
	public void nullValues() {
		JOptionPane.showMessageDialog(null,
				"Null values ​​are not allowed or incorrect values");
	}

	/**
	 * Desplega la lista de clubes en los distintos combobox.
	 * 
	 * @param teams
	 *            Serán los clubes a mostrar.
	 */
	public void showClubs(JComboBox<String> teams) {
		for (Club c : clubs)
			teams.addItem(c.getClubName());

	}

	/**
	 * Convierte de String a entero.
	 * 
	 * @param s
	 *            Parámetro String a convertir.
	 * @return Retorna el número entero convertido como resultado.
	 */
	public int toInteger(String s) {
		return Integer.parseInt(s);
	}

	/**
	 * Método que comprueba que se digiten números correctos en el marcador del
	 * partido.
	 * 
	 * @param s
	 *            Parámetro String a comprobar.
	 * @return Retorna cierto o falso según valide.
	 */
	public boolean validateInteger(String s) {
		boolean ok = false;

		Pattern patron = Pattern.compile("[0-9]{1,2}");
		Matcher mat = patron.matcher(s);

		if (mat.matches())
			ok = true;

		return ok;
	}

}
