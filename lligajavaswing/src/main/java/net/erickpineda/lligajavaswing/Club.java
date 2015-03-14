package net.erickpineda.lligajavaswing;

public class Club {
	/**
	 * Nombre del club.
	 */
	private String clubName;
	/**
	 * Partidos empatados del club.
	 */
	private int drawn;
	/**
	 * Partidos perdidos del club.
	 */
	private int lost;
	/**
	 * Puntos del club.
	 */
	private int points;
	/**
	 * Partidos ganados del club.
	 */
	private int won;
	/**
	 * Si el club ha ganado.
	 */
	private boolean hasWon = false;
	/**
	 * Si el club ha empatado.
	 */
	private boolean hasTied = false;
	/**
	 * Si el club a perdido.
	 */
	private boolean hasLost = false;

	/**
	 * Constructor de club.
	 * 
	 * @param nombre
	 *            nombre del club.
	 * @param ganados
	 *            número de partidos ganados.
	 * @param empatados
	 *            número de partidos empatados.
	 * @param perdidos
	 *            número de partidos perdidos.
	 */

	public Club(String nombre, int puntos, int ganados, int empatados,
			int perdidos) {
		this.clubName = nombre;
		this.points = puntos;
		this.won = ganados;
		this.drawn = empatados;
		this.lost = perdidos;
	}

	/**
	 * Constructor de clubs a partir de su nombre;
	 * 
	 * @param nombre
	 */
	public Club(String nombre) {
		this.clubName = nombre;
	}

	/**
	 * Método que agrega puntos un club.
	 */
	public void addPoints() {
		if (hasWon == true) {
			this.points += 3;
			hasWon = false;
		}
		if (hasTied == true) {
			this.points += 1;
			hasTied = false;
		}
		if (hasLost == true) {
			this.points += 0;
			hasLost = false;
		}
	}

	/**
	 * Método que agrega una victoria al club.
	 */
	public void addVictory() {
		this.hasWon = true;
		this.won += 1;
	}

	/**
	 * Método que agrega una derrota al club.
	 */
	public void addDefeat() {
		this.hasLost = true;
		this.lost += 1;
	}

	/**
	 * Método que agrega un empatea al club.
	 */
	public void addDrawn() {
		this.hasTied = true;
		this.drawn += 1;
	}

	/**
	 * @return retorna el nombre del Club.
	 */
	public String getClubName() {
		return clubName;
	}

	/**
	 * @return retorna los partidos empatados del Club.
	 */
	public int getDrawn() {
		return drawn;
	}

	/**
	 * @return retorna los partidos perdidos del Club.
	 */
	public int getLost() {
		return lost;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @return retorna los partidos ganados del Club.
	 */
	public int getWon() {
		return won;
	}

	/**
	 * @param clubName
	 *            cambia el nombre del Club.
	 */
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	/**
	 * @param drawn
	 *            the drawn to set.
	 */
	public void setDrawn(int drawn) {
		this.drawn = drawn;
	}

	/**
	 * @param lost
	 *            the lost to set.
	 */
	public void setLost(int lost) {
		this.lost = lost;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

	/**
	 * @param won
	 *            the won to set.
	 */
	public void setWon(int won) {
		this.won = won;
	}

	/**
	 * Retorna el club con nombre de club, partidos ganados, empatados y
	 * perdidos.
	 */
	public String toString() {
		return "The Club: " + this.getClubName() + " has points: "
				+ this.getPoints() + " has matches won: " + this.getWon()
				+ " matches drawn: " + this.getDrawn() + " matches lost "
				+ this.getLost();
	}
}
