package Programm;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Simon Borst
 * @version 1
 */
public class Angebot {
	
	public enum Status {
		IN_BEARBEITUNG, ABGESCHLOSSEN, ABGELEHNT
	}
	
	//Im Konstruktor
	private Kunde kunde;
	private final LocalDate erstellDatum;
	private int angebotsNummer;
	private static int naechsteAngebotsNummer = 20000;
	private String betreff;
	private Status status;
	
	//Seperat
	private Map<Date,String> protokoll = new TreeMap<>();
	private LinkedList<Posten> postenListe = new LinkedList<>();
	private LinkedList<ArbeitsPosten> arbeitsPostenListe = new LinkedList<>();

	public Angebot(final Kunde kunde, String betreff) {
		this.kunde = kunde;
		this.betreff = betreff;
		this.status = Status.IN_BEARBEITUNG;
		erstellDatum = LocalDate.now();
		angebotsNummer = naechsteAngebotsNummer;
		naechsteAngebotsNummer++;
	}

	public int getAngebotsNummer() {
		return angebotsNummer;
	}
	
	public void setBetreff(String betreff) {
		Date datum = new Date();
		this.betreff = betreff;
		protokoll.put(datum, "Betreff wurde geaendert zu: " + betreff);
	}
	
	public void angebotsPostenErstellen (String materialName, double materialKosten) {
		postenListe.add(new Posten(materialName, materialKosten));
	}
	
	public void angebotsPostenErstellen (String name, double stundenlohn, double stunden) {
		arbeitsPostenListe.add(new ArbeitsPosten(name, stundenlohn,stunden));
	}
	
	public void postenEntfernen(Posten posten) {
		if (!postenListe.contains(posten)) {
			throw new NoSuchElementException("Der zu entfernende Posten wurde bereits entfernt oder existiert nicht!");
		} else {			
			if (this.status.equals(Status.IN_BEARBEITUNG)) {
				Date datum = new Date();
				postenListe.remove(posten);
				protokoll.put(datum, "Der Einzelposten: " + posten.getName() + "wurde entfernt");
			} else {
				System.out.println("Eine Aenderung ist leider nicht mehr moeglich!");
			}
		}
	}

	public void arbeitsPostenEntfernen(ArbeitsPosten posten) {
		if (!arbeitsPostenListe.contains(posten)) {
			throw new NoSuchElementException("Der zu entfernende Posten wurde bereits entfernt oder existiert nicht!");
		} else {
			if (this.status.equals(Status.IN_BEARBEITUNG)) {
				Date datum = new Date();
				arbeitsPostenListe.remove(posten);
				protokoll.put(datum, "Der Einzelposten: " + posten.getName() + "wurde entfernt");
			} else {
				System.out.println("Eine Aenderung ist leider nicht mehr moeglich!");
			}
		}
	}

	public void materialKostenBearbeiten(String name, double preis, Posten materialPosten) throws IllegalAccessException {
		if (materialPosten == null) {
			throw new NullPointerException();
		} else if (preis<0) {
			throw new IllegalArgumentException("Der Materialkostenpreis kann nicht negativ sein!");
		} else if (this.status.equals(status.IN_BEARBEITUNG)) {
			Date datum = new Date();
			String oldNAme = materialPosten.getName();
			double oldPreis = materialPosten.getPreis();
			protokoll.put(datum, "In dem EinzelPosten: " + materialPosten.getName() + " wurden die Angaben Name: " + oldNAme + " und Preis: " +oldPreis + ", zu Name: "+ name + " und Preis: " + preis + " ge�ndert");
			materialPosten.setName(name);
			materialPosten.setPreis(preis);
		} else {
			throw new IllegalAccessException("Eine Aenderung ist Leider nicht mehr moeglich!");
		}
	}
	
	public void arbeitsZeitBearbeiten(double stunden, double stundenlohn, ArbeitsPosten arbeitsPosten) throws IllegalAccessException {
		if (arbeitsPosten == null) {
			throw new NullPointerException();
		} else if (stunden<0) {
			throw new IllegalArgumentException();
		} else if (this.status.equals(Status.IN_BEARBEITUNG)) {
			Date datum = new Date();
			double oldStundenAnzahl = arbeitsPosten.getStunden();
			double oldEinzelPreis = arbeitsPosten.getPreis();
			protokoll.put(datum,"Die Arbeitszeit wurde von "+ oldStundenAnzahl + "zu " + stunden + " geändert. Der Einzelpreis wurde von " + oldEinzelPreis + " zu " + stundenlohn + " ge�ndert. Neuer Postenpreis betr�gt: "+ stundenlohn * stunden);
			arbeitsPosten.setStunden(stunden);
			arbeitsPosten.setPreis(stundenlohn);
			arbeitsPosten.setPreis(stundenlohn * stunden);
		} else {
			throw new IllegalAccessException("Eine Aenderung ist Leider nicht mehr moeglich!");
		}
	}
	
	void ausdrucken() {
		this.status = Status.ABGESCHLOSSEN;
		Speicher.angebotSpeichern(this);
		System.out.println("Ausdrucken von Angebot " + angebotsNummer + " erfolgt");
	}	
	
	public Map getProtokoll() {
		return protokoll;
	}
	
	public LocalDate getErstellDatum() {
		return erstellDatum;
	}

	
	/**
	 *
	 * @author Maja Wandura
	 * @return total price of the offer
	 */
	public double getTotalPrice() {
		return postenListe.stream()
				.mapToDouble(posten -> posten.getPreis())
				.sum()
				+
				arbeitsPostenListe.stream()
						.mapToDouble(posten -> posten.getPreis())
						.sum();
	}

	/**
	 * @author Maja Wandura
	 * @return A string representation of the offer
	 */
	@Override
	public String toString() {
		String einzelPosten = postenListe.stream()
				.map(materialKosten -> materialKosten.toString())
				.collect(Collectors.joining(", "));
		
		String arbeitszeitPosten = arbeitsPostenListe.stream()
				.map(arbeitsKosten -> arbeitsKosten.toString())
				.collect(Collectors.joining(", "));
	 
		return betreff + " f�r " + kunde.getName() +
				"\nKundennummer: " + kunde.getKundenNummer() + 
				"\nAngebotsnummer: " + angebotsNummer +
				"\nAdresse/Postfach: " + kunde.getStrasse() + " / " + kunde.getPostFach() +
				"\nPLZ/Ort: " + kunde.getPLZ() + " / " + kunde.getOrt() + 
				"\nMaterial: " + einzelPosten +
				"\nArbeitszeit: " + arbeitszeitPosten +
				"\nSumme: " + getTotalPrice() + "�" +
				"\nMehrwertsteuer 19%: " + getTotalPrice() * 0.19 + "�" +
				"\nGesamtpreis: " + ((getTotalPrice() * 0.19) + getTotalPrice()) + "�" +
				"\nStatus: " + status + "\n";
	}

	public Posten getPostenByName(String name) {
		return postenListe.stream()
				.filter(posten -> posten.getName().equals(name))
				.findFirst()
				.orElseThrow(NoSuchElementException::new);

	}

	public ArbeitsPosten getArbeitspostenByName(String name) {
		return arbeitsPostenListe.stream()
				.filter(posten -> posten.getName().equals(name))
				.findFirst()
				.orElseThrow(NoSuchElementException::new);

	}
		
}
