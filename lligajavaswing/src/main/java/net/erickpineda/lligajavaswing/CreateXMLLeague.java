package net.erickpineda.lligajavaswing;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class CreateXMLLeague {
	/**
	 * Create Factory.
	 */
	private XMLOutputFactory xof;
	/**
	 * Write on the xml file.
	 */
	private XMLStreamWriter escritorXML;
	/**
	 * List of clubs.
	 */
	private List<Club> clubs;
	/**
	 * Will be the path to the file.
	 */
	private String pathToFile;

	/**
	 * Constructor of leagues passing parameter, the path to the file and a list
	 * of clubs.
	 * 
	 * @param pathFile
	 *            Path to file.
	 * @param teams
	 *            Clubs of the League.
	 */
	public CreateXMLLeague(String pathFile, List<Club> teams) {

		this.clubs = teams;
		this.pathToFile = pathFile;
	}

	/**
	 * Method that creates the output xml file.
	 */
	public void createInstance() {

		try {
			xof = XMLOutputFactory.newInstance();
			escritorXML = xof.createXMLStreamWriter(new FileWriter(pathToFile
					+ ".xml"));

			createXMLFile();

		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method that validates the name of the xml file to create.
	 * 
	 * @return Return true if the validation is correct.
	 */
	public boolean checkFileName() {
		boolean ok = false;

		Pattern patron = Pattern
				.compile("([a-zA-Z]{1,10})\\w([ 0-9a-zA-Z]{0,10})");
		Matcher mat = patron.matcher(pathToFile);

		if (mat.matches())
			ok = true;

		return ok;
	}

	/**
	 * Method that creates the XML file with your tags.
	 */
	public void createXMLFile() {
		try {
			// "<?xml version='1.0' encoding='UTF-8'?>"
			escritorXML.writeStartDocument("UTF8", "1.0");

			// Create tag league
			escritorXML.writeStartElement("league");

			// Create tag clubs
			escritorXML.writeStartElement("clubs");

			for (Club c : clubs) {
				// Create tag club
				escritorXML.writeStartElement("club");
				escritorXML.writeAttribute("name", c.getClubName());

				// Create tag points
				escritorXML.writeStartElement("points");
				escritorXML.writeCharacters(String.valueOf(c.getPoints()));
				escritorXML.writeEndElement();

				// Create tag won
				escritorXML.writeStartElement("won");
				escritorXML.writeCharacters(String.valueOf(c.getWon()));
				escritorXML.writeEndElement();

				// Create tag drawn
				escritorXML.writeStartElement("drawn");
				escritorXML.writeCharacters(String.valueOf(c.getDrawn()));
				escritorXML.writeEndElement();

				// Create tag lost
				escritorXML.writeStartElement("lost");
				escritorXML.writeCharacters(String.valueOf(c.getLost()));
				escritorXML.writeEndElement();

				// Close tag club
				escritorXML.writeEndElement();
			}

			// Close tag clubs
			escritorXML.writeEndElement();

			// Close tag league
			escritorXML.writeEndElement();

			escritorXML.writeEndDocument();
			escritorXML.flush();
			escritorXML.close();

		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}
}
