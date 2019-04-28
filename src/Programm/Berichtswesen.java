package Programm;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Sarah Merz
 * @version 1
 *
 */
public class Berichtswesen {

    private Map<Integer, Angebot> angebote = new TreeMap<>();

    public Berichtswesen() {

    }

    public void angebotHinzufuegen(Angebot angebot) {
        try {
            int kundenNummer = angebot.getKundenNummer();
            angebote.put(kundenNummer, angebot);
        } catch (NullPointerException e) {
            throw new NullPointerException(" Das Angebot konnte nicht hinzugefügt werden");
        }
    }

    public void angebotEntfernen(Angebot angebot) throws NullPointerException {
        if (!angebote.containsValue(angebot)) {
            throw new NullPointerException("Der zu entfernende Einzelposten wurde bereits entfernt oder existiert nicht!");
        } else if (angebot == null) {
            throw new NullPointerException();
        }
        angebote.remove(angebot.getKundenNummer());

    }


    public Collection<Angebot> getAngebote() {
		return angebote.values();
	}
	
	public void filterAngebote(String beginn, String ende) {
		System.out.println("Alle Angebot in dem Zeitraum von " + beginn + " bis " + ende + " sind:");
		System.out.println("....");
	}
	
}


