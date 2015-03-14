package net.erickpineda.lligajavaswing;

import java.awt.Color;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;

public class JPanelNewLeague extends JPanel {
	/**
	 * ID de la clase.
	 */
	private static final long serialVersionUID = 6920115013666487146L;
	/**
	 * Lista de clubes en la liga.
	 */
	private List<Club> clubs;
	/**
	 * Componente que llevará la lista de clubes, cuando se le da al boton add.
	 */
	private JList<String> listComponent;
	/**
	 * Mensajes de error, que parecen en el panel, cuando la acción es inválida.
	 */
	private JLabel mensajeError;
	/**
	 * Modelo que seguirá el {@code JList<String> listComponent}.
	 */
	private DefaultListModel<String> model = new DefaultListModel<String>();
	/**
	 * ScrollPane que alamacena los clubes.
	 */
	private ScrollPane scrollPane;
	/**
	 * Recoge el nombre del club a crear.
	 */
	private JTextField textFieldNameClub;
	/**
	 * Nombre de la liga y fichero xml a crear.
	 */
	private JTextField textFieldNameLeague;
	/**
	 * Valor boleano que comprueba si todo es correcto.
	 */
	private boolean todoOk = false;

	/**
	 * Crea el JPanel de crear liga.
	 * 
	 * @param owner
	 *            JDialog que pasa por parámetro cuando se crea un
	 *            {@code JPanelNewLeague}.
	 */
	public JPanelNewLeague(final JDialog owner) {
		setForeground(SystemColor.controlHighlight);
		setBorder(new CompoundBorder(null, new TitledBorder(
				UIManager.getBorder("TitledBorder.border"),
				"Insert information", TitledBorder.CENTER, TitledBorder.TOP,
				null, new Color(0, 0, 0))));

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		// Label para el nombre de la liga
		JLabel lblLeagueName = new JLabel("League name");
		springLayout.putConstraint(SpringLayout.NORTH, lblLeagueName, 26,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblLeagueName, 65,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, lblLeagueName, 156,
				SpringLayout.WEST, this);
		add(lblLeagueName);

		// TextField para recoger el nombre de la liga
		textFieldNameLeague = new JTextField();
		textFieldNameLeague.setBackground(SystemColor.textHighlightText);
		springLayout.putConstraint(SpringLayout.NORTH, textFieldNameLeague, -3,
				SpringLayout.NORTH, lblLeagueName);
		springLayout.putConstraint(SpringLayout.WEST, textFieldNameLeague, 42,
				SpringLayout.EAST, lblLeagueName);
		springLayout.putConstraint(SpringLayout.EAST, textFieldNameLeague, -63,
				SpringLayout.EAST, this);
		textFieldNameLeague.setColumns(10);
		add(textFieldNameLeague);

		// Label para el nombre del club
		final JLabel lblClubName = new JLabel("Club name");
		springLayout.putConstraint(SpringLayout.WEST, lblClubName, 0,
				SpringLayout.WEST, lblLeagueName);
		springLayout.putConstraint(SpringLayout.EAST, lblClubName, 0,
				SpringLayout.EAST, lblLeagueName);
		add(lblClubName);

		// Lista de clubs
		listComponent = new JList<String>(model);
		listComponent.setBackground(SystemColor.textHighlightText);
		springLayout.putConstraint(SpringLayout.NORTH, listComponent, 0,
				SpringLayout.NORTH, lblClubName);
		springLayout.putConstraint(SpringLayout.WEST, listComponent, 0,
				SpringLayout.WEST, textFieldNameLeague);
		springLayout.putConstraint(SpringLayout.EAST, listComponent, 0,
				SpringLayout.EAST, textFieldNameLeague);

		// Contenedor de lista de clubs
		scrollPane = new ScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0,
				SpringLayout.NORTH, lblClubName);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0,
				SpringLayout.WEST, textFieldNameLeague);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0,
				SpringLayout.EAST, textFieldNameLeague);
		scrollPane.add(listComponent);
		add(scrollPane);

		// TextField para recoger el nombre del club
		textFieldNameClub = new JTextField();
		textFieldNameClub.setBackground(SystemColor.textHighlightText);
		springLayout.putConstraint(SpringLayout.SOUTH, lblClubName, -6,
				SpringLayout.NORTH, textFieldNameClub);
		springLayout.putConstraint(SpringLayout.WEST, textFieldNameClub, 0,
				SpringLayout.WEST, lblLeagueName);
		springLayout.putConstraint(SpringLayout.EAST, textFieldNameClub, 0,
				SpringLayout.EAST, lblLeagueName);
		textFieldNameClub.setColumns(10);
		add(textFieldNameClub);

		// Botón add
		JButton btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(0, 0, 0));
		btnAdd.setBackground(SystemColor.control);
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addClub();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnAdd, 120,
				SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, textFieldNameClub, -6,
				SpringLayout.NORTH, btnAdd);
		springLayout.putConstraint(SpringLayout.WEST, btnAdd, 0,
				SpringLayout.WEST, lblLeagueName);
		springLayout.putConstraint(SpringLayout.EAST, btnAdd, 6,
				SpringLayout.EAST, lblLeagueName);
		add(btnAdd);

		// Botón remove
		JButton btnRemove = new JButton("Remove");
		btnRemove.setForeground(new Color(0, 0, 0));
		btnRemove.setBackground(SystemColor.control);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 0,
				SpringLayout.SOUTH, btnRemove);
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeClub();
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnRemove, 6,
				SpringLayout.SOUTH, btnAdd);
		springLayout.putConstraint(SpringLayout.WEST, btnRemove, 0,
				SpringLayout.WEST, lblLeagueName);
		springLayout.putConstraint(SpringLayout.EAST, btnRemove, 0,
				SpringLayout.EAST, btnAdd);
		add(btnRemove);

		// Botón finish
		JButton btnFinish = new JButton("Finish");
		btnFinish.setForeground(new Color(0, 0, 0));
		btnFinish.setBackground(SystemColor.control);
		springLayout.putConstraint(SpringLayout.NORTH, btnFinish, 33,
				SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnFinish, 252,
				SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, btnFinish, 0,
				SpringLayout.EAST, textFieldNameLeague);
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clubList();
				finish(owner);
			}
		});
		add(btnFinish);

		// Label para los distintos mensajes de error
		mensajeError = new JLabel("");
		mensajeError.setForeground(Color.RED);
		springLayout.putConstraint(SpringLayout.NORTH, mensajeError, 6,
				SpringLayout.SOUTH, textFieldNameLeague);
		springLayout.putConstraint(SpringLayout.EAST, mensajeError, -42,
				SpringLayout.EAST, this);
		mensajeError.setFont(new Font("Arial", Font.BOLD, 12));
		add(mensajeError);

	}

	/**
	 * Método que se ejecuta cuando se oprime el botón {@code JButton btnAdd}.
	 */
	protected void addClub() {

		Pattern patron = Pattern.compile("[a-zA-Z 0-9]{1,20}\\w");
		Matcher mat = patron.matcher(textFieldNameClub.getText());

		if (mat.matches()) {

			model.addElement(textFieldNameClub.getText().trim());
			textFieldNameClub.setText("");
			mensajeError.setText("");

		} else {
			JOptionPane.showMessageDialog(null,
					"The name of the club should be at least 2"
							+ " and 21 characters. [a-zA-Z 0-9]{1,20}\\w",
					"Name Club Incorrect", JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Método que crea una liga, pasando por parámetro los clubes procesados a
	 * partir del xml.
	 * 
	 * @return Retorna un JScrollPane con los clubes de liga.
	 */
	public JScrollPane createLeague() {
		League ligaNueva = new League(clubs);
		return ligaNueva.showTable();
	}

	/**
	 * Método que rellena el {@code ArrayList<Club> clubs} a partir del modelo
	 * {@code DefaultListModel<String> model} creado.
	 */
	protected void clubList() {

		clubs = new ArrayList<Club>();
		Object[] obj = model.toArray();

		for (int i = 0; i < obj.length; i++) {
			if (obj[i] instanceof String) {
				clubs.add(new Club((String) obj[i]));
			}
		}
	}

	/**
	 * Método que se ejecuta cuando se oprime el botón {@code JButton btnFinish}
	 * . Comprueba que todos los valores sean válidos.
	 * 
	 * @param owner
	 *            Pasa por prámetro el JDialog que se ejecuta.
	 */
	protected void finish(final JDialog owner) {

		CreateXMLLeague liga;

		if (textFieldNameLeague.getText().length() > 0) {
			liga = new CreateXMLLeague(textFieldNameLeague.getText(), clubs);

			if (liga.checkFileName() == true) {

				if (clubs.size() <= 1) {
					mensajeError.setText("Deben existir dos equipos mínimo");
				} else {
					liga.createInstance();
					todoOk = true;
					owner.setVisible(false);
				}
			} else {
				mensajeError.setText("El nombre de la liga es inválido");
			}
		} else {
			mensajeError.setText("El nombre de la liga no puede ser nulo");
		}
	}

	/**
	 * 
	 * @return Retorna una lista de clubes.
	 */
	public List<Club> getClubs() {
		return clubs;
	}

	/**
	 * 
	 * @return Retorna el nombre de la liga, que será también el nombre del
	 *         fichero.
	 */
	public String getLeagueName() {
		return textFieldNameLeague.getText();
	}

	/**
	 * 
	 * @return Retorna un componente JList.
	 */
	public JList<String> getListComponent() {
		return listComponent;
	}

	/**
	 * 
	 * @return Retorna un ScrollPane, quien contiene un JList.
	 */
	public ScrollPane getNewLeaguePanel() {
		return scrollPane;
	}

	/**
	 * Método que devuelve true, si todas las validaciones de cualquier tipo se
	 * cumplen.
	 * 
	 * @return Retorna false, si hay algún error en la validación.
	 */
	public boolean isTodoOk() {
		return todoOk;
	}

	/**
	 * Método que detecta el valor o los valores seleccionados en el
	 * {@code JList<String> listComponent} y los remueve.
	 */
	protected void removeClub() {

		while (listComponent.getSelectedIndex() >= 0)
			model.remove(listComponent.getSelectedIndex());

	}

	/**
	 * Método que permite cambiar el club por otro.
	 * 
	 * @param clubs
	 *            Parámetro a cambiar.
	 */
	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}

}
