package de.stl.saar.prog3.i18n;

import java.util.ResourceBundle;

/**
 * Kapselt die Zugriffe auf die Ressourcendateien fuer die Internationalisierung. In diesem
 * kleinen Beispiel wird nur eine Ressourcendatei verwendet, doch es kann durchaus sinnvoll
 * sein, mehrere solcher Dateien zu definieren, z.B. fuer Beschriftungen, Fehlermeldungen usw. 
 * @author christopher
 *
 */
public class I18nUtil {
	/**
	 * Der Basisname fuer die Ressourcendatei. 
	 */
	//private static final String I18N_BASENAME_COMPONENTS = "i18n.components";
	private static final String I18N_BASENAME_MESSAGES = "i18n.messages";
	//private static final String I18N_BASENAME_STRINGS = "i18n.strings";
	/**
	 * Die Ressourcendatei fuer alle Texte.
	 */
	//private static ResourceBundle resourceBundleComponents;
	private static ResourceBundle resourceBundleMessages;
	//private static ResourceBundle resourceBundleStrings;
	
	/**
	 * Der Initialisierungsblock laedt die Ressourcendateien, so dass wir darauf zugreifen
	 * koennen.
	 */
	static {
		/*resourceBundleComponents = ResourceBundle.getBundle
				(I18N_BASENAME_COMPONENTS);*/
		resourceBundleMessages = ResourceBundle.getBundle
				(I18N_BASENAME_MESSAGES);
		/*resourceBundleStrings = ResourceBundle.getBundle
				(I18N_BASENAME_STRINGS);*/
	}
	public static ResourceBundle getMessagesResourceBundle() {
		return resourceBundleMessages;
	}
}
