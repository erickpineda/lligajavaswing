package net.erickpineda.lligajavaswing;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class League {
	/**
	 * Lista de clubs de la liga.
	 */
	private List<Club> clubs;
	/**
	 * Las cabeceras de la tabla.
	 */
	private String[] headers = { "Club", "Points", "Won", "Drawn", "Lost" };
	/**
	 * Almacena los datos de cada club.
	 */
	private DefaultTableModel myModel = new DefaultTableModel();
	/**
	 * Creará una tabla para almacenar los clubes y su información.
	 */
	private JTable myTable;

	/**
	 * Constructor de una liga a partir de una lista de clubes.
	 * 
	 * @param teams
	 *            Serán los clubes que procesará la liga.
	 */
	public League(List<Club> teams) {
		setClubs(teams);
		myTable = new JTable(myModel);
	}

	/**
	 * Itera las posiciones del array de cabeceras y crea una columna en la
	 * tabla para cada una.
	 */
	protected void generateHeaders() {
		for (String header : headers)
			myModel.addColumn(header);

	}

	/**
	 * Método que almacena en un array de objetos las cabeceras de la tabla.
	 * 
	 * @param i
	 *            Parámetro que será la posición del club, que se está iterando.
	 * @return Retorna un array de objetos que luego se almacenará a un
	 *         DefaultTableModel.
	 */
	protected Object[] generateLine(int i) {
		Object[] data = new Object[5];

		Club c = clubs.get(i);

		data[0] = c.getClubName();
		data[1] = c.getPoints();
		data[2] = c.getWon();
		data[3] = c.getDrawn();
		data[4] = c.getLost();

		return data;
	}

	/**
	 * Itera la lista de clubs, con la información del XML alamacenado.
	 */
	protected void generateTableModel() {

		for (@SuppressWarnings("unused")
		Club c : clubs)
			if (myTable.getRowCount() < clubs.size())
				myModel.addRow(generateLine(myTable.getRowCount()));

	}

	/**
	 * @return the clubs
	 */
	public List<Club> getClubs() {
		return clubs;
	}

	/**
	 * @param clubs
	 *            the clubs to set
	 */
	public void setClubs(List<Club> clubs) {
		this.clubs = clubs;
	}

	/**
	 * Método que genera las cabeceras y el contenido de la tabla.
	 * 
	 * @return Retorna un scrollPane, con la tabla, el modelo y la información
	 *         ya almacenada.
	 */
	public JScrollPane showTable() {

		generateHeaders();
		generateTableModel();

		JScrollPane scrollPane = new JScrollPane(myTable);
		return scrollPane;
	}

}
