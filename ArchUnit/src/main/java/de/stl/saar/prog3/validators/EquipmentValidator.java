package de.stl.saar.prog3.validators;

public class EquipmentValidator {
	public static boolean isEquipmentNameEmpty(final String firstName) {
		return !StringValidator.hasContent(firstName);
	}
	
	public static boolean isWeightEmpty(final String weight) {
		return !StringValidator.hasContent(weight);
	}	
	
	public static boolean isWeightInvalid(final double weight) {
		if (weight > 0.0) {
			return false;
		} else {
			return true;
		}
	}
}
