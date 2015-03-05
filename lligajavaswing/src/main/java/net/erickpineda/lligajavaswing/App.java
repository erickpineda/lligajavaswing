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
import javax.swing.JTextField;
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

public class App extends JFrame {

	/**
	 * ID de la clase.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Panel contenedor de los componentes.
	 */
	private JPanel contentPane;

	private File myFile;
	private JScrollPane xcroll;

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

			}
		});
		contentPane.add(btnEnterANew, BorderLayout.SOUTH);

		xcroll = new JScrollPane();
		contentPane.add(xcroll, BorderLayout.CENTER);
	}

	public void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNewLeague = new JMenuItem("New League");
		mntmNewLeague.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				final JDialog f = new JDialog((JFrame) null, true);
				f.setIconImage(Toolkit.getDefaultToolkit().getImage(
						"src/main/resources/mantis.png"));

				f.setResizable(false);
				f.setBounds(450, 200, 400, 315);
				f.getContentPane().setLayout(new BorderLayout());

				final JPanelNewLeague nuevaLiga = new JPanelNewLeague(f);
				f.getContentPane().add(nuevaLiga, BorderLayout.CENTER);

				int ans = JOptionPane.showConfirmDialog(f,
						"¿Want you create another league?", "Create League",
						JOptionPane.YES_NO_OPTION);

				if (ans == JOptionPane.YES_OPTION) {

					contentPane.remove(xcroll);
					f.setTitle("Create League");
					f.setVisible(true);
				}
				if (nuevaLiga.isTodoOk() == true) {

					xcroll = nuevaLiga.showTable();
					contentPane.add(xcroll, BorderLayout.CENTER);
					setContentPane(contentPane);
					App.this.setTitle(nuevaLiga.getLeagueName() + ".xml");

				}
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
				contentPane.remove(xcroll);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: "
							+ fc.getSelectedFile().getName());

					myFile = fc.getSelectedFile();

					File selectedFile = fc.getSelectedFile();
					App.this.repaint();
					setContentPane(openXML(selectedFile));

					try {
						App.this.setTitle(selectedFile.getCanonicalPath()
								.toLowerCase());
					} catch (IOException excep) {
						excep.printStackTrace();
					}
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
		mnMatches.setIcon(new ImageIcon("src/main/resources/trident.png"));
		menuBar.add(mnMatches);

	}

	public JPanel openXML(File selectedFile) {

		SAXParserFactory spf = SAXParserFactory.newInstance();
		SAXParser parser;
		OpenLeague liga = null;

		try {

			parser = spf.newSAXParser();
			liga = new OpenLeague();

			parser.parse(selectedFile, liga);
			xcroll = liga.showTable();

			contentPane.add(xcroll, BorderLayout.CENTER);

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentPane;
	}

	protected void keyBoardEvents() {
		JPanel p = new JPanel();
		ActionMap actionMap = new ActionMapUIResource();

		actionMap.put("action_save", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				System.out.println("Save action performed.");
				if (myFile != null) {
					openXML(myFile);
				}
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
