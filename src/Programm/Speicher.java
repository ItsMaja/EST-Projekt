package Programm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * @author Lisette Ester
 * @version 1
 */
public class Speicher {

	private Map<Integer,Angebot> angebote;
	private Map<Integer, Kunde> kundenListe;
	
	Speicher() {
		angebote = new TreeMap<>();
		kundenListe = new TreeMap<>();
	}
	
	public Collection<Angebot> getAngebote() {
		return angebote.values();
	}

	public void angebotSpeichern(Angebot angebot) {
		if (angebot == null) {
			throw new NullPointerException("Das Angebit muss erst initialisiert werden!");
		} else {
			int kundenNummer = angebot.getKundenNummer();
			angebote.put(kundenNummer, angebot);
		}
	}
	
	public void bestandsKundeAufnehmen(Kunde kunde) {
		if (kunde == null) {
			throw new NullPointerException("Der Kunde muss erst initialisiert werden!");
		} else { 
			kundenListe.put(kunde.getKundenNummer(), kunde);
		}
	}
	
	public void bestandsKundeLoeschen(int kundenNummer) throws Exception {
		if (!kundenListe.containsKey(kundenNummer)) {
			throw new Exception("In der Kundenliste ist kein Kunde mit der eingegebenen Kundennummer vorhanden!");
		} else {
			kundenListe.remove(kundenNummer);
		}
	}
	
	public Angebot getAngebotViaKundenNummer(int kundenNummer) {
		if (angebote.containsKey(kundenNummer)) {
			return angebote.get(kundenNummer);
		} else {
			throw new IllegalArgumentException("Die Kundennummer ist keinem Angebot zugeordnet");
		}
	}
	
	public boolean containsKunde(int kundenNummer) {
		return kundenListe.containsKey(kundenNummer);
	}
	
	/**
	 * Returns the customer object for the specified name
	 * @param name Name of customer
	 * @return The customer with the specified name
	 * @throws NoSuchElementException If the customer does not exist, throw an exception
	 */
	Kunde getCustomerbyName(String name) throws NoSuchElementException {
		return kundenListe.values().stream()
				.filter(customer -> customer.getName().equals(name))
				.findFirst()
				.orElseThrow(NoSuchElementException::new);
	}
	
	/*
	public boolean containsKunde(String name) {
		int x = 1;
		for(String s : kundenNamenListe) {
			if (name.equals(s)) {
				x = 0;
			}
		}
		if (x == 0) {
			return true;
		} else {
			return false;
		}
	}
	*/

}
