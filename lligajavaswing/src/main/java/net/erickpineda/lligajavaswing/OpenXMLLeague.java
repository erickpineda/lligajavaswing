package net.erickpineda.lligajavaswing;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class OpenXMLLeague extends DefaultHandler {
	/**
	 * Lista de clubs.
	 */
	private List<Club> clubs = null;
	/**
	 * Si la etiqueta point existe.
	 */
	private boolean TAG_POINT = false;
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
	 * Número de puntos por partidos del club.
	 */
	private int puntos;
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
	 * Constructor de liga.
	 */
	public OpenXMLLeague() {
		clubs = new ArrayList<Club>();
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
	 * 
	 * @return Retorna la lista de clubs.
	 */
	public List<Club> getClubs() {
		return clubs;
	}

	/**
	 * Inicio de la etiqueta XML. Método que comprueba que las etiquetas won,
	 * drawn y lost existan en el XML y almacena el nombre del club en una
	 * lista.
	 */
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {

		if (qName.equals("club"))
			if (!attributes.getValue("name").isEmpty())
				clubName = attributes.getValue(0);

		if (qName.equalsIgnoreCase("points"))
			TAG_POINT = true;

		if (qName.equalsIgnoreCase("won"))
			TAG_WON = true;

		if (qName.equalsIgnoreCase("drawn"))
			TAG_DRAWN = true;

		if (qName.equalsIgnoreCase("lost"))
			TAG_LOST = true;

	}

	/**
	 * Contenido de caracteres, que tiene la etiqueta XML. Método que recoge los
	 * caracteres que hay dentro de las etiquetas won, drawn, lost y los
	 * almacena en una variable.
	 */
	public void characters(char ch[], int start, int length)
			throws SAXException {

		if (TAG_POINT) {
			String p = new String(ch, start, length);
			puntos = Integer.parseInt(p);
			TAG_POINT = false;
		}
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
			clubs.add(new Club(clubName, puntos, victorias, empates, derrotas));

	}
}
