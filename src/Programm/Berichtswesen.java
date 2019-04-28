package Programm;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

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
}


