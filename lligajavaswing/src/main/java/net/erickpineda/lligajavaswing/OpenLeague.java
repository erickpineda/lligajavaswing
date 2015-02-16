package net.erickpineda.lligajavaswing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OpenLeague extends DefaultHandler {
	/**
	 * Lista de clubs.
	 */
	private List<Club> clubs = null;
	/**
	 * Si la etiqueta won existe.
	 */
	private boolean TAG_WON = false;
	/**
	 * Si la etiqueta drawn existe.
	 */
	private boolean TAG_DRAWN = false;
	/**
	 * Si la etiqueta lost existe.
	 */
	private boolean TAG_LOST = false;
	/**
	 * Variable que tendrá el nombre del club.
	 */
	private String clubName = "";
	/**
	 * Número de victorias del club.
	 */
	private int victorias;
	/**
	 * Número de empates del club.
	 */
	private int empates;
	/**
	 * Número de derrotas del club.
	 */
	private int derrotas;
	/**
	 * Creará una tabla para almacenar los clubes y su información.
	 */
	private JTable myTable;
	/**
	 * Almacena los datos de cada club.
	 */
	private DefaultTableModel myModel = new DefaultTableModel();
	/**
	 * Las cabeceras de la tabla.
	 */
	private String[] headers = { "Club", "Won", "Drawn", "Lost" };

	/**
	 * Constructor de liga.
	 */
	public OpenLeague() {
		clubs = new ArrayList<Club>();
		myTable = new JTable();
		myTable.setModel(myModel);
	}

	/**
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

	/**
	 * Método que almacena en un array de objetos las cabeceras de la tabla.
	 * 
	 * @param i
	 *            Parámetro que será la posición del club, que se está iterando.
	 * @return Retorna un array de objetos que luego se almacenará a un
	 *         DefaultTableModel.
	 */
	protected Object[] generateLine(int i) {
		Object[] data = new Object[4];

		Club c = clubs.get(i);

		data[0] = c.getClubName();
		data[1] = c.getWon();
		data[2] = c.getDrawn();
		data[3] = c.getLost();

		return data;
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
	 * Itera la lista de clubs, con la información del XML alamacenado.
	 */
	protected void generateTableModel() {

		for (@SuppressWarnings("unused")
		Club c : clubs)
			if (myTable.getRowCount() < clubs.size())
				myModel.addRow(generateLine(myTable.getRowCount()));

	}

	/**
	 * Fin del documento XML.
	 */
	public void endDocument() throws SAXException {
		for (Club c : clubs)
			System.out.println(c.toString());
	}

	/**
	 * Inicio de la etiqueta XML. Método que comprueba que las etiquetas won,
	 * drawn y lost existan en el XML y almacena el nombre del club en una
	 * lista.
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {

		if (qName.equalsIgnoreCase("won"))
			TAG_WON = true;

		if (qName.equalsIgnoreCase("drawn"))
			TAG_DRAWN = true;

		if (qName.equalsIgnoreCase("lost"))
			TAG_LOST = true;

		if (qName.equals("club"))
			if (!attributes.getValue("name").isEmpty())
				clubName = attributes.getValue(0);

	}

	/**
	 * Contenido de caracteres, que tiene la etiqueta XML. Método que recoge los
	 * caracteres que hay dentro de las etiquetas won, drawn, lost y los
	 * almacena en una variable.
	 */
	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (TAG_WON) {
			String v = new String(ch, start, length);
			victorias = Integer.parseInt(v);
			TAG_WON = false;
		}
		if (TAG_DRAWN) {
			String e = new String(ch, start, length);
			empates = Integer.parseInt(e);
			TAG_DRAWN = false;
		}
		if (TAG_LOST) {
			String d = new String(ch, start, length);
			derrotas = Integer.parseInt(d);
			TAG_LOST = false;
		}
	}

	/**
	 * Inicio de la etiqueta XML. Método que crea los clubs, con toda la
	 * información necesaria.
	 */
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (qName.equalsIgnoreCase("club"))
			clubs.add(new Club(clubName, victorias, empates, derrotas));

	}
}
