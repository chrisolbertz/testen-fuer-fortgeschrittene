package de.stl.saar.prog3.i18n;

import java.util.ResourceBundle;

public abstract class I18nMessagesUtil {
	private static final String ERROR_INVALID_FIELDS = "error-invalid-fields";
	private static final String ERROR_NO_COMPANY_SELECTED = "error-no-company-selected";
	private static final String ERROR_NO_EQUIPMENT_SELECTED = "error-no-equipment-selected";
	private static final String ERROR_NO_SELECTION = "error-no-selection";
	private static final String ERROR_NO_PERSON_SELECTION = "error-no-person-selected";
	private static final String ERROR_NO_VALID_EQUIPMENT_NAME = "error-no-valid-equipment-name";
	private static final String ERROR_NO_VALID_FIRSTNAME = "error-no-valid-firstname";
	private static final String ERROR_NO_VALID_COMPANY_NAME = "error-no-valid-company-name";
	private static final String ERROR_NO_VALID_EMPLOYEE_COUNT = "error-no-valid-employee-count";
	private static final String ERROR_NO_VALID_WEIGHT = "error-no-valid-weight";
	private static final String ERROR_NO_VALID_LASTNAME = "error-no-valid-lastname";
	private static final String ERROR_NO_VALID_STREET = "error-no-valid-street";
	private static final String ERROR_NO_VALID_ZIP = "error-no-valid-zip";
	private static final String ERROR_NO_VALID_ZIP_INTEGER = "error-no-valid-zip-integer";
	private static final String ERROR_PLEASE_SELECT_COMPANY = "error-please-select-company";
	private static final String ERROR_PLEASE_SELECT_EQUIPMENT = "error-please-select-equipment";
	private static final String MESSAGE_ABOUT = "message-about";
	private static final String MESSAGE_ABOUT_PROGRAM = "message-about-program";
	private static final String MESSAGE_AUTHORS = "message-authors";
	private static final String MESSAGE_CORRECT_INVALID_FIELDS = "message-correct-invalid-fields";
	private static final String MESSAGE_PLEASE_SELECT_PERSON = "message-please-select-person";
	
	private static ResourceBundle resourceBundle;
	
	static {
		resourceBundle = I18nUtil.getMessagesResourceBundle();
	}

	public static String getErrorInvalidFieldsString() {
		return resourceBundle.getString(ERROR_INVALID_FIELDS);
	}
	
	public static String getErrorNoSelectionString() {
		return resourceBundle.getString(ERROR_NO_SELECTION);
	}
	
	public static String getErrorNoPersonSelectionString() {
		return resourceBundle.getString(ERROR_NO_PERSON_SELECTION);
	}
	
	public static String getErrorNoValidFirstnameString() {
		return resourceBundle.getString(ERROR_NO_VALID_FIRSTNAME);
	}
	
	public static String getErrorNoValidCompanyNameString() {
		return resourceBundle.getString(ERROR_NO_VALID_COMPANY_NAME);
	}
	
	public static String getErrorNoValidEmployeeCountString() {
		return resourceBundle.getString(ERROR_NO_VALID_EMPLOYEE_COUNT);
	}
	
	public static String getErrorNoValidLastnameString() {
		return resourceBundle.getString(ERROR_NO_VALID_LASTNAME);
	}
	
	public static String getErrorNoValidStreetString() {
		return resourceBundle.getString(ERROR_NO_VALID_STREET);
	}
	
	public static String getErrorNoValidZipString() {
		return resourceBundle.getString(ERROR_NO_VALID_ZIP);
	}
	
	public static String getErrorNoValidZipIntegerString() {
		return resourceBundle.getString(ERROR_NO_VALID_ZIP_INTEGER);
	}
	
	public static String getMessageCorrectInvalidFieldsString() {
		return resourceBundle.getString(MESSAGE_CORRECT_INVALID_FIELDS);
	}
	
	public static String getMessagePleaseSelectPersonString() {
		return resourceBundle.getString(MESSAGE_PLEASE_SELECT_PERSON);
	}
	
	public static String getMessageAboutString() {
		return resourceBundle.getString(MESSAGE_ABOUT);
	}
	
	public static String getMessageAboutProgramString() {
		return resourceBundle.getString(MESSAGE_ABOUT_PROGRAM);
	}
	
	public static String getMessageAuthorsString() {
		return resourceBundle.getString(MESSAGE_AUTHORS);
	}

	public static String getErrorNoCompanySelectionString() {
		return resourceBundle.getString(ERROR_NO_COMPANY_SELECTED);
	}
	
	public static String getErrorNoEquipmentSelectionString() {
		return resourceBundle.getString(ERROR_NO_EQUIPMENT_SELECTED);
	}

	public static String getMessagePleaseSelectCompanyString() {
		return resourceBundle.getString(ERROR_PLEASE_SELECT_COMPANY);
	}
	
	public static String getMessagePleaseSelectEquipmentString() {
		return resourceBundle.getString(ERROR_PLEASE_SELECT_EQUIPMENT);
	}

	public static String getErrorNoValidEquipmentNameString() {
		return resourceBundle.getString(ERROR_NO_VALID_EQUIPMENT_NAME);
	}
	
	public static String getErrorNoValidWeightString() {
		return resourceBundle.getString(ERROR_NO_VALID_WEIGHT);
	}
}
