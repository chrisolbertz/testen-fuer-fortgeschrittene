package de.stl.saar.prog3.validators;

/**
 * Validiert die Felder einer Person bei der Eingabe.
 * @author christopher
 *
 */
public class PersonValidator {
	public static boolean isFirstNameEmpty(String firstName) {
		return !StringValidator.hasContent(firstName);
	}
	
	public static boolean isLastNameEmpty(String lastName) {
		return !StringValidator.hasContent(lastName);
	}	
}
