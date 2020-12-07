package de.stl.saar.prog3.validators;

/**
 * Validiert die Felder einer Firma bei der Eingabe.
 * @author christopher
 *
 */
public final class CompanyValidator {
	public static boolean isCompanyNameEmpty(final String firstName) {
		return !StringValidator.hasContent(firstName);
	}
	
	public static boolean isEmployeeCountEmpty(final String employeeCount) {
		return !StringValidator.hasContent(employeeCount);
	}	
	
	public static boolean isEmployeeCountInvalid(final int employeeCount) {
		if (employeeCount > 0) {
			return false;
		} else {
			return true;
		}
	}
}
