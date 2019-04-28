package Programm;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * @author Jonathan Baumann
 * @version 1
 */
public class Kunde {

	public static ArrayList<String> kunden = new ArrayList();
	public static Map<String, Integer> liste = new TreeMap();
	
	private String name;
	private String ort;
	private String strasse;
	private int postFach;
	private int hausNummer;
	private int plz;
	private int kundenNummer;
	
	
	public Kunde(String name, String ort, String strasse, int postFach, int hausNummer, int plz) {
		this.name = name;
		this.ort = ort;
		this.strasse = strasse;
		this.postFach = postFach;
		this.hausNummer = hausNummer;
		if(kunden.contains(name)) {
			this.kundenNummer = liste.get(name);
		} else {
			kunden.add(name);
			Random zufall = new Random();
			this.kundenNummer = zufall.nextInt(90000);
			liste.put(name, kundenNummer);
		}
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
