package net.erickpineda.lligajavaswing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ActionMapUIResource;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class App extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		setTitle("Union Of Clubs");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"src/main/resources/mantis.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		createMenu();
		controlQ();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// contentPane.add(createTable(), BorderLayout.CENTER);

		JLabel lblFullLeagueTable = new JLabel("Full League Table");
		lblFullLeagueTable.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblFullLeagueTable, BorderLayout.NORTH);

		JButton btnEnterANew = new JButton("Enter a new sport session");
		contentPane.add(btnEnterANew, BorderLayout.SOUTH);

	}

	public void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNewLeague = new JMenuItem("New League");
		mntmNewLeague.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// setVisible(false);

				final JFrame f = new JFrame("Create League");
				f.setIconImage(Toolkit.getDefaultToolkit().getImage(
						"src/main/resources/mantis.png"));

				f.setResizable(false);
				f.setBounds(100, 100, 400, 315);
				f.getContentPane().setLayout(new BorderLayout());
				final JPanelNewLeague nuevaLiga = new JPanelNewLeague();
				f.getContentPane().add(nuevaLiga, BorderLayout.CENTER);

				int ans = JOptionPane.showConfirmDialog(f,
						"¿Want you create another league?", "Create League",
						JOptionPane.YES_NO_OPTION);

				if (ans == JOptionPane.YES_OPTION) {
					f.setVisible(true);
				}

				// getContentPane().add(nuevaLiga, BorderLayout.CENTER);
				// nuevaLiga.setVisible(true);
				// f.getContentPane().add(createTable(), BorderLayout.SOUTH);
				// f.pack();

				// JOptionPane.showMessageDialog(null, nuevaLiga);

			}
		});

		mntmNewLeague.setIcon(new ImageIcon(App.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		mnFile.add(mntmNewLeague);

		JSeparator separator = new JSeparator();
		mnFile.add(separator);

		JMenuItem mntmOpenLeague = new JMenuItem("Open League");
		mntmOpenLeague.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Only xml files", "xml");

				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(App.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: "
							+ fc.getSelectedFile().getName());
					
					File selectedFile = fc.getSelectedFile();
					openXML(selectedFile);

				}

			}
		});
		mntmOpenLeague
				.setIcon(new ImageIcon(
						App.class
								.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		mnFile.add(mntmOpenLeague);

		JMenuItem mntmSaveLeague = new JMenuItem("Save League");
		mntmSaveLeague.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser fc = new JFileChooser();
				JTextField filename = new JTextField(), dir = new JTextField();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Only xml files", "xml");

				fc.setFileFilter(filter);
				int returnVal = fc.showSaveDialog(App.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					filename.setText(fc.getSelectedFile().getName());
					dir.setText(fc.getCurrentDirectory().toString());
				}
				if (returnVal == JFileChooser.CANCEL_OPTION) {
					filename.setText("You pressed cancel");
					dir.setText("");
				}
			}
		});
		mntmSaveLeague
				.setIcon(new ImageIcon(
						App.class
								.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mnFile.add(mntmSaveLeague);

		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);

		JMenuItem mntmExit = new JMenuItem("Exit CTRL+Q");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mntmExit.setIcon(new ImageIcon(App.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mnFile.add(mntmExit);

		JMenu mnMatches = new JMenu("Matches");
		menuBar.add(mnMatches);

	}

	public Component createTable() {
		// array bidimencional de objetos con los datos de la tabla
		Object[][] data = {
				{ "Trident", new Integer(22), new Integer(5), new Integer(7),
						new Integer(0) },
				{ "DDW Makers", new Integer(5), new Integer(5), new Integer(5),
						new Integer(5) },
				{ "Mega Sports Jrs", new Integer(5), new Integer(5),
						new Integer(2), new Integer(5) },
				{ "Sporting Dikora", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Reading", new Integer(5), new Integer(5), new Integer(5),
						new Integer(5) },
				{ "Power United", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Deportivo Calarca", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Purakas", new Integer(5), new Integer(5), new Integer(5),
						new Integer(5) },
				{ "Cacique FC", new Integer(5), new Integer(5), new Integer(5),
						new Integer(5) },
				{ "Red Devils", new Integer(5), new Integer(5), new Integer(5),
						new Integer(5) },
				{ "Real Valle", new Integer(5), new Integer(5), new Integer(5),
						new Integer(5) },
				{ "Defensor Sporting", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Deportivo Luna", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Deportivo Mustang", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Inter Sport", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Los Del Sur", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Rampa Juniors", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Revolucion Vinotinto", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) } };

		// array de String's con los tÌtulos de las columnas
		String[] columnNames = { "Club", "Points", "Won", "Drawn", "Lost" };

		// se crea la Tabla
		final JTable table = new JTable(data, columnNames);
		table.setModel(new DefaultTableModel(new Object[][] {
				{ "Trident", new Integer(22), new Integer(5), new Integer(7),
						new Integer(0) },
				{ "DDW Makers", new Integer(5), new Integer(5), new Integer(5),
						new Integer(5) },
				{ "Mega Sports Jrs", new Integer(5), new Integer(5),
						new Integer(2), new Integer(5) },
				{ "Sporting Dikora", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Reading", new Integer(5), new Integer(5), new Integer(5),
						new Integer(5) },
				{ "Power United", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Deportivo Calarca", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Purakas", new Integer(5), new Integer(5), new Integer(5),
						new Integer(5) },
				{ "Cacique FC", new Integer(5), new Integer(5), new Integer(5),
						new Integer(5) },
				{ "Red Devils", new Integer(5), new Integer(5), new Integer(5),
						new Integer(5) },
				{ "Real Valle", new Integer(5), new Integer(5), new Integer(5),
						new Integer(5) },
				{ "Defensor Sporting", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Deportivo Luna", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Deportivo Mustang", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Inter Sport", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Los Del Sur", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Rampa Juniors", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) },
				{ "Revolucion Vinotinto", new Integer(5), new Integer(5),
						new Integer(5), new Integer(5) }, }, new String[] {
				"Club", "Points", "Won", "Drawn", "Lost" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(104);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));

		// Creamos un JscrollPane y le agregamos la JTable
		JScrollPane scrollPane = new JScrollPane(table);

		// Agregamos el JScrollPane al contenedor
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		return scrollPane;
	}

	public void openXML(File selectedFile) {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser parser;
		OpenLeague liga = null;
		JScrollPane scrollPane = null;

		try {

			parser = spf.newSAXParser();
			liga = new OpenLeague();

			parser.parse(
					new File(App.class
							.getResource("/" + selectedFile.getName())
							.getFile()), liga);

			scrollPane = liga.showTable();

			contentPane.add(scrollPane, BorderLayout.CENTER);
			setContentPane(contentPane);

		} catch (ParserConfigurationException e2) {
			e2.printStackTrace();
		} catch (SAXException e2) {
			e2.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	protected void controlQ() {
		JPanel p = new JPanel();
		ActionMap actionMap = new ActionMapUIResource();

		actionMap.put("action_save", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				System.out.println("Save action performed.");
			}
		});

		actionMap.put("action_exit", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				System.out.println("Exit action performed.");
				System.exit(0);
			}
		});

		InputMap keyMap = new ComponentInputMap(p);

		keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
				java.awt.Event.CTRL_MASK), "action_save");

		keyMap.put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q,
				java.awt.Event.CTRL_MASK), "action_exit");

		SwingUtilities.replaceUIActionMap(p, actionMap);
		SwingUtilities.replaceUIInputMap(p, JComponent.WHEN_IN_FOCUSED_WINDOW,
				keyMap);

		getRootPane().add(p);
	}

}
