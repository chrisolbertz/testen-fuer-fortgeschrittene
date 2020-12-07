package de.stl.saar.prog3.utils;

import de.stl.saar.prog3.constants.FXConstants;
import de.stl.saar.prog3.validators.StringValidator;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Enthaelt einige Hilfsmethoden fuer die Arbeit mit einem JavaFX-Programm:
 * <ul>
 * 	<li>Erzeugt einen Standardtitel.</li>
 * </ul>
 * @author christopher
 *
 */
public class FXUtils {
	private FXUtils() {
	}
	
	/**
	 * Erzeugt einen Standarddialogtitel. Dieser besteht aus dem Namen des
	 * Programms, gefolgt von einem Doppelpunkt und dann einem spezifischen
	 * Titel fuer den Dialog.
	 * 
	 * @param aSpecificDialogName
	 *            Der spezifische Titel fuer den Dialog.
	 * @return Der Standarddialogtitel.
	 */
	public static String getDialogTitle(String aSpecificDialogName) {
		if (StringValidator.hasContent(aSpecificDialogName)) {
			StringBuilder result = new StringBuilder(
					FXConstants.APPLICATION_NAME);
			result.append(": ");
			result.append(aSpecificDialogName);
			return result.toString();
		} else {
			return null;
		}
	}
	
	/**
	 * Zeigt einen Fehlerdialog an.
	 * @param title Der Titel des Dialogs. Der Name der Anwendung wird durch Doppelpunkt
	 * getrennt vor dem Titel angezeigt.
	 * @param header Die Überschrift, die über der Nachricht angezeigt wird.
	 * @param message Die Nachricht, die in dem Dialog angezeigt wird.
	 */
	public static void showErrorDialog(String title, String header, 
			String message) {
		Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle(FXUtils.getDialogTitle(title));
    	alert.setHeaderText(header);
    	alert.setContentText(message);
    	alert.showAndWait();
    }
	
	/**
	 * Zeigt einen Informationsdialog an.
	 * @param title Der Titel des Dialogs. Der Name der Anwendung wird durch Doppelpunkt
	 * getrennt vor dem Titel angezeigt.
	 * @param header Die Überschrift, die über der Nachricht angezeigt wird.
	 * @param message Die Nachricht, die in dem Dialog angezeigt wird.
	 */
	public static void showInformationDialog(String title, String header, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(FXUtils.getDialogTitle(title));
    	alert.setHeaderText(header);
    	alert.setContentText(message);
    	alert.showAndWait();
    }
	
	/**
	 * Zeigt einen Warndialog an.
	 * @param title Der Titel des Dialogs. Der Name der Anwendung wird durch Doppelpunkt
	 * getrennt vor dem Titel angezeigt.
	 * @param header Die Überschrift, die über der Nachricht angezeigt wird.
	 * @param message Die Nachricht, die in dem Dialog angezeigt wird.
	 */
	public static void showWarningDialog(String title, String header, String message) {
		Alert alert = new Alert(AlertType.WARNING);
    	alert.setTitle(FXUtils.getDialogTitle(title));
    	alert.setHeaderText(header);
    	alert.setContentText(message);
    	alert.showAndWait();
    }
}