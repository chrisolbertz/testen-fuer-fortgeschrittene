package de.stl.saar.prog3.utils;

public class StringUtils {
	public static final String EMPTY_STRING = "";
	
	private StringUtils() {
	}
	
	public static boolean isNotEmpty(final String aString) {
		if (aString == null) {
			return false;
		}
		return !aString.trim().isEmpty();
	}

	public static boolean isEmpty(final String aString) {
		if (aString == null) {
			return true;
		}
		return aString.trim().isEmpty();
	}
	
	public static boolean areStringsEqual(final String string1, final String string2) {
		if (string1.equals(string2)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Ermittelt das erste Vorkommen eines bestimmten Zeichens und gibt alle Zeichen
	 * davor zurück.
	 * <br/>
	 * Beispiel: character ist ':' und theString ist abc:def. Zurückgegeben wird dann abc.
	 * @param character Das Zeichen, nach dem in theString gesucht wird.
	 * @param theString Der String, aus dem alle Zeichen vor character ermittelt werden sollen.
	 * @return Alle Zeichen in theString vor character.
	 */
	public static String everythingBeforeCharacter(final char character, final String theString) {
		final int indexOfCharacter = theString.indexOf(character);
		if (indexOfCharacter < 0) {
			return theString;
		}
		final String beforeCharacter = theString.substring(0, indexOfCharacter);
		return beforeCharacter;
	}
	
	public static void test() {}
}
