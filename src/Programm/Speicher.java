package Programm;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Lisette Ester
 * @version 1
 */
public class Speicher {

	private List<Angebot> angebote = new LinkedList<>();
	private List<Kunde> kunden = new LinkedList<>();

	void angebotSpeichern(Angebot angebot) {
			angebote.add(angebot);
	}
	
	void kundeAufnehmen(Kunde kunde) {
		kunden.add(kunde);
	}

	public void kundeLoeschen(int kundenNummer) throws NoSuchElementException {
		Kunde toDelete = kunden.stream()
				.filter(kunde -> kunde.getKundenNummer() == kundenNummer)
				.findFirst()
				.orElseThrow(NoSuchElementException::new);
		kunden.remove(toDelete);
	}
	
	public boolean containsKunde(int kundenNummer) {
		return kunden.stream().anyMatch(kunde -> kunde.getKundenNummer() == kundenNummer);
	}
	
	/**
	 * Returns the customer object for the specified name
	 * @param name Name of customer
	 * @return The customer with the specified name
	 * @throws NoSuchElementException If the customer does not exist, throw an exception
	 * @author Maja Wandura
	 */
	Kunde getCustomerbyName(String name) throws NoSuchElementException {
		return kunden.stream()
				.filter(customer -> customer.getName().equals(name))
				.findFirst()
				.orElseThrow(NoSuchElementException::new);
	}
}
