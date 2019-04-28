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
	
	public static void generiereBericht() {
	    Speicher.getAngebote().forEach(System.out::println);
	}
	
}


