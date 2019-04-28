package Programm;

import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import Programm.AngebotsPosten.Art;

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
	private final Date erstellDatum;
	private int angebotsNummer;
	private static int naechsteAngebotsNummer = 20000;
	private String betreff;
	private Status status;
	
	//Seperat
	private Map<Date,String> protokoll = new TreeMap<>();
	private LinkedList<AngebotsPosten> angebotsPostenListe = new LinkedList<>();
			
	public Angebot(final Kunde kunde, String betreff) {
		this.kunde = kunde;
		this.betreff = betreff;
		this.status = Status.IN_BEARBEITUNG;
		erstellDatum = new Date();
		angebotsNummer = naechsteAngebotsNummer;
		naechsteAngebotsNummer++;
	}
	
	public void setBetreff(String betreff) {
		Date datum = new Date();
		this.betreff = betreff;
		protokoll.put(datum, "Betreff wurde ge�ndert zu: " + betreff);
	}
	
	public AngebotsPosten angebotsPostenErstellen (MaterialKosten materialKosten) {
		angebotsPostenListe.add(materialKosten);
		return materialKosten;
	}
	
	public AngebotsPosten angebotsPostenErstellen (ArbeitsKosten arbeitsKosten) {
		angebotsPostenListe.add(arbeitsKosten);
		return arbeitsKosten;
	}
	
	public void angebotsPostenEntfernen(MaterialKosten materialKosten) throws Exception {
		if (!angebotsPostenListe.contains(materialKosten)) {
			throw new Exception("Die zu entfernenden Materialkosten wurden bereits entfernt oder existieren nicht!");
		} else if (materialKosten == null) {
			throw new NullPointerException();
		} else {			
			if (this.status.equals(status.IN_BEARBEITUNG)) {
				Date datum = new Date();
				angebotsPostenListe.remove(materialKosten);
				protokoll.put(datum,"Der Einzelposten: " + materialKosten.getAngebotsPostenName() + "wurde entfernt");
			} else {
				System.out.println("Eine �nderung ist Leider nicht mehr moeglich!");
			}
		}
	}
	
	public void angebotsPostenEntfernen(ArbeitsKosten arbeitsKosten) throws Exception {
		if (!angebotsPostenListe.contains(arbeitsKosten)) {
			throw new Exception("Die zu entfernenden Arbeitskosten wurden bereits entfernt oder existieren nicht!");
		} else if (arbeitsKosten == null) {
			throw new NullPointerException();
		} else {	
			if (this.status.equals(status.IN_BEARBEITUNG)) {
			Date datum = new Date();
			angebotsPostenListe.remove(arbeitsKosten);
			protokoll.put(datum, "Die Arbeitszeit " + arbeitsKosten.getAngebotsPostenName() + " mit dem zugehörigen Stundenlohn: " + arbeitsKosten.getAngebotsPostenPreis() + " wurde entfernt");
			} else {
				System.out.println("Eine Änderung ist leider nicht mehr möglich!");
			}
		}
	}
	
	public void materialKostenBearbeiten(String name, double preis, MaterialKosten materialKosten) throws Exception {
		if (materialKosten == null) {
			throw new NullPointerException();
		} else if (preis<0) {
			throw new IllegalArgumentException("Der Materialkostenpreis kann nicht negativ sein!");
		} else if (this.status.equals(status.IN_BEARBEITUNG)) {
			Date datum = new Date();
			String oldNAme = materialKosten.getAngebotsPostenName();
			double oldPreis = materialKosten.getAngebotsPostenPreis();
			protokoll.put(datum, "In dem EinzelPosten: " + materialKosten.getAngebotsPostenName() + " wurden die Angaben Name: " + oldNAme + " und Preis: " +oldPreis + ", zu Name: "+ name + " und Preis: " + preis + " ge�ndert"); 
			materialKosten.setAngebotsPostenName(name);
			materialKosten.setAngebotsPostenPreis(preis);
		} else {
			System.out.println("Eine �nderung ist Leider nicht mehr m�glich!");
		}
	}
	
	public void arbeitsZeitBearbeiten(double stundenAnzahl,double einzelPreis, ArbeitsKosten arbeitsKosten) throws Exception {
		if (arbeitsKosten == null) {
			throw new NullPointerException();
		} else if (stundenAnzahl<0) {
			throw new IllegalArgumentException();
		} else if (einzelPreis != 80 || einzelPreis != 150) {
			throw new IllegalArgumentException("Der Einzelpreis darf lediglich nur 150,00€ oder 80,00€ betragen");
		} else if(this.status.equals(status.IN_BEARBEITUNG)) {
			Date datum = new Date();
			double oldStundenAnzahl = arbeitsKosten.getStundenAnzahl();
			double oldEinzelPreis = arbeitsKosten.getEinzelPreis();
			protokoll.put(datum,"Die Arbeitszeit wurde von "+ oldStundenAnzahl + "zu " + stundenAnzahl + " geändert. Der Einzelpreis wurde von " + oldEinzelPreis + " zu " + einzelPreis + " ge�ndert. Neuer Postenpreis betr�gt: "+ einzelPreis * stundenAnzahl);
			arbeitsKosten.setStundenAnzahl(stundenAnzahl);
			arbeitsKosten.setEinzelPreis(einzelPreis);
			arbeitsKosten.setAngebotsPostenPreis(einzelPreis * stundenAnzahl);
		} else {
			System.out.println("Eine �nderung ist Leider nicht mehr m�glich!");
		}
	}
	
	void ausdrucken() {
		this.status = Status.ABGESCHLOSSEN;
		Speicher.angebotSpeichern(this);
		System.out.println("Ausdrucken erfolgt");
	}	
	
	public Map getProtokoll() {
		return protokoll;
	}
	
	int getKundenNummer() {
		return this.kunde.getKundenNummer();
	}
	
	public Date getErstellDatum() {
		return erstellDatum;
	}
	
	/**
	 *
	 * @author Maja Wandura
	 * @return total price of the offer
	 */
	public double getTotalPrice() {
		double totalPrice = angebotsPostenListe.stream()
				.mapToDouble(posten -> posten.getAngebotsPostenPreis())
				.sum();
		/*
		double workTimePrice = .stream()
				.mapToDouble(posten -> posten.getStundenSatz() * posten.getZeit())
				.sum();
		*/
		return totalPrice;
	}

	/*
	 * 
	 * Bin mir nicht sicher ob das alles zu 100% stimmt.
	 * 
	 * Hab es getestet und nichts gefunden aber kann durchaus auch sein, dass ich etwas �bersehen habe..
	 * 
	 * Ansonsten hab ich die Methode so angepasst, dass die Konsole ca das gleiche Layout hat wie 
	 * das Beispiel-Angebot in ILIAS
	 * 
	 */
	/**
	 * @author Maja Wandura
	 * @return A string representation of the offer
	 */
	@Override
	public String toString() {
		String material = angebotsPostenListe.stream()
				.filter(materialKosten -> materialKosten.getArt() == Art.Materialkosten)
				.map(materialKosten -> materialKosten.toString())
				.collect(Collectors.joining(", "));
		
		String workTime = angebotsPostenListe.stream()
				.filter(arbeitsKosten -> arbeitsKosten.getArt() == Art.Arbeitskosten)
				.map(arbeitsKosten -> arbeitsKosten.toString())
				.collect(Collectors.joining(", "));
	 
		return betreff + " f�r " + kunde.getName() +
				"\nKundennummer: " + kunde.getKundenNummer() + 
				"\nAngebotsnummer: " + angebotsNummer +
				"\nAdresse/Postfach: " + kunde.getStrasse() + " / " + kunde.getPostFach() +
				"\nPLZ/Ort: " + kunde.getPLZ() + " / " + kunde.getOrt() + 
				"\nMaterial: " + material +
				"\nArbeitszeit: " + workTime +
				"\nSumme: " + getTotalPrice() + "�" +
				"\nMehrwertsteuer 19%: " + getTotalPrice() * 0.19 + "�" +
				"\nGesamtpreis: " + ((getTotalPrice() * 0.19) + getTotalPrice()) + "�" +
				"\nStatus: " + status + "\n";
	}
		
}
