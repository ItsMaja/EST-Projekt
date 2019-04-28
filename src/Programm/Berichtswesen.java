package Programm;

import javax.naming.AuthenticationException;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Sarah Merz
 * @version 1
 *
 */
class Berichtswesen {

    /*
    Beispiel Erzeugung eines künstlichen Datums zum Test:

    LocalDate date = LocalDate.of(2018, 10, 23);
     */
	
	static void generiereBericht() {
	    Speicher.getAngebote().forEach(System.out::println);
	}

    static void generiereBericht(LocalDate from, LocalDate to) {
        Speicher.getAngebote().stream()
                .filter(angebot -> angebot.getErstellDatum().isAfter(from) && angebot.getErstellDatum().isBefore(to))
                .forEach(System.out::println);
    }

    static void generiereJahresbericht(int jahr) {
	    Speicher.getAngebote().stream()
                .filter(angebot -> angebot.getErstellDatum().getYear() == jahr)
                .forEach(System.out::println);
    }

    static void generiereJahresumsatz(int jahr) throws AuthenticationException {
	    if (AuthenticationSystem.getUserRole() != AuthenticationSystem.Role.GESCHAEFTSFUEHRUNG) {
	        throw new AuthenticationException();
        }

        System.out.printf("%-20s %s\n", "Angebotsnummer", "Gesamtpreis");

        Speicher.getAngebote().stream()
                .filter(angebot -> angebot.getErstellDatum().getYear() == jahr)
                .map(angebot -> String.format("%-20s %s€", angebot.getAngebotsNummer(), angebot.getTotalPrice()))
                .forEach(System.out::println);

        double totalRevenue = Speicher.getAngebote().stream().mapToDouble(Angebot::getTotalPrice).sum();

        System.out.printf("%-20s %s€", "Gesamtumsatz", totalRevenue);
    }
}


