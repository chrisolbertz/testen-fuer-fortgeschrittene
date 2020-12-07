package de.stl.saar.prog3.validators;

import de.stl.saar.prog3.exceptions.StringIsEmptyException;

/**
 * Enthaelt Methoden zur Validierung von Strings. 
 * @author Christopher
 *
 */
public class StringValidator {
	/**
	 * Prueft, ob ein String Inhalt hat. Dabei werden fuehrende und folgende
	 * Leerzeichen geloescht.
	 * @param aString Der zu pruefende String.
	 * @return true, wenn der String Inhalt hat, false, wenn der String leer
	 * oder null ist.
	 */
	public static boolean hasContent(String aString) {
		if (aString == null || aString.trim().isEmpty() ) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Prueft, ob ein String Inhalt hat. Dabei werden fuehrende und folgende
	 * Leerzeichen geloescht.
	 * @param aString Der zu pruefende String.
	 * @throws StringIsEmptyException Wird geworfen, wenn der String leer
	 * oder null ist.
	 */
	public static void checkForContent(String aString) 
			throws StringIsEmptyException {
		if (hasContent(aString) == false) {
			throw new StringIsEmptyException();
		}
	}
}
