package Programm;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Im Rahmen des Tests werden Methoden direkt verwendet, welche der Benutzer über das GUI aufrufen kann.
 * Entsprechende Exceptions und Rückmeldungen werden von der GUI durch Mitteilungen ausgegeben.
 * Über das GUI-Formular kann der Benutzer direkt die Konstruktoren aufrufen
 *
 * @author Maja Wandura
 */
class DemoClassTest {

    @BeforeEach
    void init() {

        Kunde bosch = new Kunde("Bosch","Teststadt","Musterstraße" , 2,78,89866);
        Speicher.kundeSpeichern(bosch);
        Angebot angebotFuerBosch = new Angebot(bosch, "Tueren aus Esche");
        angebotFuerBosch.angebotsPostenErstellen("Bretter aus Eschenholz",320);
        angebotFuerBosch.angebotsPostenErstellen("Schreiner", 80, 4.5);
        angebotFuerBosch.angebotsPostenErstellen("Schleifmaschine", 150, 1.0);
        Speicher.angebotSpeichern(angebotFuerBosch);
    }

    @Test
    void loginTestSuccesful() {

        assertTrue(AuthenticationSystem.validateCredentials(AuthenticationSystem.Role.MITARBEITER,"platin"));
        assertEquals(AuthenticationSystem.Role.MITARBEITER, AuthenticationSystem.getUserRole());
    }

    @Test
    void loginTestFailed() {

        assertFalse(AuthenticationSystem.validateCredentials(AuthenticationSystem.Role.GESCHAEFTSFUEHRUNG,"platin"));
        assertNull(AuthenticationSystem.getUserRole());

    }

    @Test
    void testeNeuenKundeHinzufuegen() {
        String customerName = "KinderSchokolade";

        // Mitarbeiter versucht, Kunden in der Datenbank zu finden und
        // erhält Rückmeldung dass dieser noch nicht im System hinterlegt ist
        assertThrows(NoSuchElementException.class, () -> Speicher.getCustomerbyName("KinderSchokolade"));

        // Der neue Kunde hat eine eindeutige Kundennummer, die sich prüfen lässt
        int kundennummer = Kunde.getNaechsteKundennummer();

        // Der Mitarbeiter legt einen neuen Kunden an
        Kunde kunde = new Kunde("KinderSchokolade","Teststadt","Musterstraße" , 8,78,89866);
        Speicher.kundeSpeichern(kunde);

        assertEquals(kundennummer,kunde.getKundenNummer());
        assertTrue(Speicher.containsKunde(kundennummer));

    }

    @Test
    void testAngebotserstellungSuccesful() {
        /*Der Mitarbeiter beginnt nun mit der Angebotserstellung und holt sich den Kunden aus der Datenbank. Anschließend wird
        *das neue Angebot erstellt.
        */
        Speicher.kundeSpeichern(new Kunde("Miele","Teststadt","Musterstraße" , 2,78,89866));

        Kunde miele = Speicher.getCustomerbyName("Miele");
        Angebot angebotFuerMiele = new Angebot(miele, "Bartheke aus Eiche");
        angebotFuerMiele.angebotsPostenErstellen("Bretter aus Eichenholz",320);
        angebotFuerMiele.angebotsPostenErstellen("Schreiner",80, 4.5);
        angebotFuerMiele.ausdrucken();

        assertTrue(Speicher.containsAngebot(angebotFuerMiele.getAngebotsNummer()));
    }

    @Test
    void testAngebotspostenÄndernSuccesful() throws IllegalAccessException {

        Angebot angebotFuerBosch = Speicher.getAngebotByNummer(20000);
        ArbeitsPosten angebotPosten = angebotFuerBosch.getArbeitspostenByName("Schreiner");
        angebotFuerBosch.arbeitsZeitBearbeiten(5.5,80, angebotPosten);
        assertEquals(5.5, angebotPosten.getStunden());

    }

    @Test
    void testAngebotspostenÄndernFailed() {
        Angebot angebotTest = new Angebot(Speicher.getCustomerbyName("Bosch"),"Tischplatte");
        ArbeitsPosten test2 = new ArbeitsPosten("Schreiner",80,3.5);
        angebotTest.ausdrucken();

        assertThrows(IllegalAccessException.class,
                () -> angebotTest.arbeitsZeitBearbeiten(5.5,80, test2));

    }

    @Test
    void betreffHinzufuegenSuccesful() {

    }
    @Test
    void erstelleAngebotspostenSuccesful() {

    }

    @Test
    void entferneAngebotspostenFailed() {

    }

    @Test
    void angebotAusdruckenSuccesful() {

    }
}