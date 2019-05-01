/*
package Programm;

import Programm.AuthenticationSystem.Role;

import javax.naming.AuthenticationException;
import java.util.NoSuchElementException;
import java.util.Scanner;

*/
/**
 * This class serves as a demonstration to show the basic functionality of the code
 * @author Maja Wandura
 *//*

public abstract class DemoClass {

	public static void main (String[] args) {

		// Login
		try (Scanner s = new Scanner(System.in)) {
			System.out.print("Herzlich willkommen! Bitte geben Sie Ihre Rolle ein (0 für kaufmännische Mitarbeiter, " +
					"1 für die Geschäftsführung)\nRolle (0/1): ");
			int roleInput = s.nextInt();
			s.nextLine();

			System.out.print("Passwort: ");
			String password = s.nextLine();

			Role role = Role.values()[roleInput];
			boolean status = AuthenticationSystem.validateCredentials(role, password);
			System.out.println(status ? "Willkommen zurück " + role : "Zugang verweigert");
		}

		// Init
		Kunde siemens = new Kunde("Siemens","Teststadt","Testecke" , 2,78,87866);
		Kunde bimbo = new Kunde("Bimbo","Teststadt","Testecke", 3, 58,87866);
		Kunde phillips = new Kunde("Phillips","Teststadt","Testecke",4, 76,87866);
		Kunde daimler = new Kunde("Daimler","Teststadt","Testecke", 5 ,745,87866);

		Angebot angebot1 = new Angebot(siemens,"Schrankt�r");
		Angebot angebot2 = new Angebot(bimbo,"Tisch mit vier Ecken");
		Angebot angebot3 = new Angebot(phillips,"K�chentheke");
		Angebot angebot4 = new Angebot(daimler,"Werkbank");

		Speicher.kundeSpeichern(siemens);
		Speicher.kundeSpeichern(bimbo);
		Speicher.kundeSpeichern(phillips);
		Speicher.kundeSpeichern(daimler);
		Speicher.angebotSpeichern(angebot1);
		Speicher.angebotSpeichern(angebot2);
		Speicher.angebotSpeichern(angebot3);
		Speicher.angebotSpeichern(angebot4);

		// Kunde kommt rein

		String customerName = "Bosch";
		Kunde bosch;

		// Kunde bereits vorhanden? Dann aus Datenbank holen, sonst neu erzeugen
		try {
			bosch = Speicher.getCustomerbyName(customerName);
		} catch (NoSuchElementException e) {
			bosch = new Kunde("Bosch", "Teststadt", "Teststra�e", 6, 45, 324324);
		}

		// Angebot wird erstellt

		String betreff = "Neue T�ren";
		Angebot newOffer = new Angebot(bosch, betreff);

		// Materialposten erstellen
		//EinzelPosten einzelPosten = new EinzelPosten("Holzplatten 2x1m", 100);
		MaterialKosten materialKosten = new MaterialKosten("Holzplatten 2x1m" , 100);
		newOffer.angebotsPostenErstellen(materialKosten);

		// Arbeitszeitposten erstellen
		//ArbeitsZeit arbeitsZeit = new ArbeitsZeit(100, 60);
		ArbeitsKosten arbeitsKosten = new ArbeitsKosten("Zu Materialposten 1", 2, 80);
		newOffer.angebotsPostenErstellen(arbeitsKosten);

		// Im Speicher ablegen
		Speicher.angebotSpeichern(newOffer);

		// Zweites Angebot erstellen
		Angebot secondOffer = new Angebot(
				new Kunde("AMG GmbH", "Testdorf", "Testweg", 7 , 34, 345436), "Armaturen"
		);

		secondOffer.angebotsPostenErstellen(new ArbeitsKosten("Zu Materialposten 2", 3, 150));
		secondOffer.angebotsPostenErstellen(new MaterialKosten("Eichenfurnier", 20000));

		Speicher.angebotSpeichern(secondOffer);

		// Berichte erzeugen
		// System.out.println("Bericht:");
		//Berichtswesen.generiereBericht();

		try {
			Berichtswesen.generiereJahresumsatz(2019);
		} catch (AuthenticationException e) {
			System.out.println("Kein Zugriff auf die Jahresumsätze für Mitarbeiter!");
		}

	}

}
*/
