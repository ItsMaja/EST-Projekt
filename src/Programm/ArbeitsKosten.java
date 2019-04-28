package Programm;

public class ArbeitsKosten extends AngebotsPosten {

	private double stundenAnzahl;
	private double einzelPreis;
	
	public ArbeitsKosten(String angebotsPostenName, double stundenAnzahl, double einzelPreis) {
		super(angebotsPostenName, stundenAnzahl * einzelPreis, Art.Arbeitskosten);
		this.einzelPreis = einzelPreis;
		this.stundenAnzahl = stundenAnzahl;
	}

	public double getEinzelPreis() {
		return einzelPreis;
	}

	public void setEinzelPreis(double einzelPreis) {
		this.einzelPreis = einzelPreis;
		setAngebotsPostenPreis(einzelPreis * this.stundenAnzahl);
	}

	public double getStundenAnzahl() {
		return stundenAnzahl;
	}

	public void setStundenAnzahl(double stundenAnzahl) {
		this.stundenAnzahl = stundenAnzahl;
		setAngebotsPostenPreis(stundenAnzahl * this.einzelPreis);
	}
	
	/**
	 * @author Maja Wandura
	 * @return A string representation of worktime and hourly wage
	 */
	@Override
	public String toString() {
		return stundenAnzahl + " Stunden zu je " + einzelPreis + "€  => Gesamtkosten: " + stundenAnzahl*einzelPreis;
	}

}
