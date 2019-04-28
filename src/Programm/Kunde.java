package Programm;

/**
 * @author Jonathan Baumann
 * @version 1
 */
public class Kunde {

	// Hält die Nummer für den nächsten Kunden vor
	static private int naechsteKundennummer = 1000;

	private String name;
	private String ort;
	private String strasse;
	private int postFach;
	private int hausNummer;
	private int plz;
	private int kundenNummer;
	
	
	Kunde(String name, String ort, String strasse, int postFach, int hausNummer, int plz) {
		this.kundenNummer = naechsteKundennummer;
		naechsteKundennummer++;
		this.name = name;
		this.ort = ort;
		this.strasse = strasse;
		this.postFach = postFach;
		this.hausNummer = hausNummer;
		this.plz = plz;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setOrt(String ort) {
		this.ort = ort;
	}
	
	public void setHausNummer(int hausNummer) {
		this.hausNummer = hausNummer;
	}
	
	public void setPlz(int plz) {
		this.plz = plz;
	}
	
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	
	public void setPostFach(int postFach) {
		this.postFach = postFach;
	}
	
	public int getKundenNummer() {
		return this.kundenNummer;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getStrasse() {
		return this.strasse;
	}
	
	public int getPostFach() {
		return this.postFach;
	}
	
	public int getPLZ() {
		return this.plz;
	}
	
	public String getOrt() {
		return this.ort;
	}
	
}
