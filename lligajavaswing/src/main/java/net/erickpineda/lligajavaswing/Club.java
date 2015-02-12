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
	 * Partidos ganados del club.
	 */
	private int won;

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
	public Club(String nombre, int ganados, int empatados, int perdidos) {
		this.clubName = nombre;
		this.setWon(ganados);
		this.setDrawn(empatados);
		this.setLost(perdidos);
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
		return "The Club: " + this.getClubName() + " has matches won: "
				+ this.getWon() + " matches drawn: " + this.getDrawn()
				+ " matches lost " + this.getLost();
	}
}
