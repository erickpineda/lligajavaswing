package net.erickpineda.lligajavaswing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ComponentInputMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
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
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ActionMapUIResource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * 
 * @author Erick Pineda
 *
 */
public class App extends JFrame {
	/**
	 * ID de la clase.
	 */
	private static final long serialVersionUID = 1L;
	private List<Club> listaClubes;

	final JFileChooser fc = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Only xml files", "xml");

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
	 * Panel contenedor de los componentes.
	 */
	private JPanel contentPane;
	/**
	 * JScrollPane vacío, que contendrá la tabla de clubes.
	 */
	private JScrollPane xcroll;

	/**
	 * Create the frame.
	 */
	public App() {
		setTitle("Union Of Clubs");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				"src/main/resources/mantis.png"));

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 450, 300);

		createMenu();
		keyBoardEvents();

		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new CompoundBorder(null,
				new TitledBorder(UIManager.getBorder("TitledBorder.border"),
						"", TitledBorder.CENTER, TitledBorder.TOP, null,
						new Color(0, 0, 0))));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JLabel lblFullLeagueTable = new JLabel("Full League Table");
		lblFullLeagueTable.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblFullLeagueTable, BorderLayout.NORTH);

		JButton btnEnterANew = new JButton("Enter a new sport session");
		btnEnterANew.setForeground(new Color(0, 0, 0));
		btnEnterANew.setBackground(SystemColor.scrollbar);

		btnEnterANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createNewSportSession();
			}
		});
		contentPane.add(btnEnterANew, BorderLayout.SOUTH);

		xcroll = new JScrollPane();
		contentPane.add(xcroll, BorderLayout.CENTER);
	}

	/**
	 * Crea el menú con sus distintas opciones.
	 */
	public void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNewLeague = new JMenuItem("New League");
		mntmNewLeague.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNewLeague();
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
				openXMLLeague();
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
				if (listaClubes != null)
					saveLeague();
				else
					JOptionPane.showMessageDialog(null,
							"You need to create a league.");

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
		mnMatches.setIcon(new ImageIcon("src/main/resources/trident.png"));
		menuBar.add(mnMatches);

	}

	/**
	 * Método que abrirá una ventana nueva para crear una nueva liga.
	 */
	public void createNewLeague() {
		final JDialog f = new JDialog((JFrame) null, true);
		f.setIconImage(Toolkit.getDefaultToolkit().getImage(
				"src/main/resources/mantis.png"));

		f.setResizable(false);
		f.setBounds(450, 200, 400, 315);
		f.getContentPane().setLayout(new BorderLayout());

		final JPanelNewLeague nuevaLiga = new JPanelNewLeague(f);
		f.getContentPane().add(nuevaLiga, BorderLayout.CENTER);

		if (listaClubes == null) {
			confirmation(f, false);
			confirmationTodoOk(nuevaLiga);
		} else {
			confirmation(f, true);
			confirmationTodoOk(nuevaLiga);
		}
	}

	/**
	 * Método que pasa como parámetro un JDialog y un boleano, para comprobar si
	 * existe una liga y se desea crear otra.
	 * 
	 * @param f
	 *            Parámetro JDialog a pasar.
	 * @param confirmed
	 *            Confirmar si existe ya una liga.
	 */
	private void confirmation(final JDialog f, boolean confirmed) {

		if (confirmed == false) {
			f.setTitle("Create League");
			f.setVisible(true);

		} else {
			int ans = JOptionPane.showConfirmDialog(f,
					"¿Want you create another league?", "Create League",
					JOptionPane.YES_NO_OPTION);

			if (ans == JOptionPane.YES_OPTION) {
				f.setTitle("Create League");
				f.setVisible(true);
			}
		}
	}

	private void confirmationTodoOk(final JPanelNewLeague nuevaLiga) {
		if (nuevaLiga.isTodoOk() == true) {

			contentPane.remove(xcroll);
			xcroll = nuevaLiga.createLeague();
			contentPane.add(xcroll, BorderLayout.CENTER);
			setContentPane(contentPane);

			App.this.setTitle(nuevaLiga.getLeagueName() + ".xml*"
					+ " - ( Unsaved )");

			listaClubes = nuevaLiga.getClubs();
		}
	}

	/**
	 * Crear partidos, entre los equipos existentes.
	 */
	private void createNewSportSession() {

		final JDialog f = new JDialog((JFrame) null, true);
		f.setResizable(false);
		f.setBounds(450, 200, 450, 300);
		f.getContentPane().setLayout(new BorderLayout());

		if (listaClubes != null) {
			JPanelMatch partido = new JPanelMatch(listaClubes, f);
			f.getContentPane().add(partido, BorderLayout.CENTER);
			f.setVisible(true);

			contentPane.remove(xcroll);
			xcroll = partido.createLeague();
			contentPane.add(xcroll, BorderLayout.CENTER);
			App.this.repaint();

			setContentPane(contentPane);
			listaClubes = partido.getClubs();

		} else {
			nullValues();
		}
	}

	/**
	 * Método que responde según las acciones del teclado.
	 */
	protected void keyBoardEvents() {
		JPanel p = new JPanel();
		ActionMap actionMap = new ActionMapUIResource();

		actionMap.put("action_save", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				System.out.println("Save action performed.");
				if (listaClubes != null)
					saveLeague();
				else
					nullValues();
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

	/**
	 * Método que procesará un fichero XML, y lo convertirá en tabla.
	 * 
	 * @param selectedFile
	 *            Fichero seleccionado, a partir del FileChooser.
	 * @return Retorna un JPanel nuevo para procesar los nuevos datos.
	 */
	public JPanel processXML(File selectedFile) {

		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser parser;
		OpenXMLLeague liga = new OpenXMLLeague();

		try {

			parser = spf.newSAXParser();
			parser.parse(selectedFile, liga);
			xcroll = liga.createLeague();

			contentPane.add(xcroll, BorderLayout.CENTER);
			listaClubes = liga.getClubs();

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentPane;
	}

	/**
	 * Método que abre por medio del FileChooser, un fichero XML para procesarlo
	 * luego.
	 */
	public void openXMLLeague() {

		fc.setFileFilter(filter);
		int returnVal = fc.showOpenDialog(App.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {

			try {

				System.out.println("You chose to open this file: "
						+ fc.getSelectedFile().getName());

				if (fc.getSelectedFile().getName().endsWith(".xml")) {
					contentPane.remove(xcroll);
					App.this.repaint();
					setContentPane(processXML(fc.getSelectedFile()));

					App.this.setTitle(fc.getSelectedFile().getCanonicalPath()
							.toLowerCase());
				} else {
					errorFile();
				}

			} catch (IOException excep) {
				excep.printStackTrace();
			}
		} else if (returnVal == JFileChooser.CANCEL_OPTION) {

		} else {
			errorFile();
		}

	}

	/**
	 * Guardará la liga que se esté creando o modificando.
	 */
	public void saveLeague() {

		fc.setFileFilter(filter);
		int returnVal = fc.showSaveDialog(App.this);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			CreateXMLLeague liga = new CreateXMLLeague(fc.getSelectedFile()
					.getAbsolutePath(), listaClubes);
			liga.createInstance();

			JOptionPane.showMessageDialog(null, "File created!",
					"File created!", JOptionPane.INFORMATION_MESSAGE);

			App.this.setTitle(fc.getSelectedFile().getAbsolutePath());

		}
	}

	private void errorFile() {
		JOptionPane.showMessageDialog(null, "The selected file is not XML");
	}

	private void nullValues() {
		JOptionPane.showMessageDialog(null, "Error: There is null values");
	}

}
