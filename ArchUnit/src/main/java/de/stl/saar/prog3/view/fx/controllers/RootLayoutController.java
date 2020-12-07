package de.stl.saar.prog3.view.fx.controllers;

import de.stl.saar.prog3.i18n.I18nMessagesUtil;
import de.stl.saar.prog3.utils.FXUtils;
import de.stl.saar.prog3.view.fx.FxSceneManager;
import javafx.fxml.FXML;

/**
 * Der Controller fuer das Root-Layout. Dieses enthaelt das grundlegende
 * Aussehen der Anwendung und stellt die Basis-Funktionen zur Verfuegung.
 * 
 */
public class RootLayoutController {
    private FxSceneManager sceneManager;

    public void setSceneManager(FxSceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
   
    /**
     * Oeffnet einen Dialog.
     */
    @FXML
    private void onAboutClicked() {
    	FXUtils.showInformationDialog(I18nMessagesUtil.getMessageAboutString(), 
    			I18nMessagesUtil.getMessageAboutProgramString(), 
    			I18nMessagesUtil.getMessageAuthorsString());
    }

    /**
     * Schliesst das Programm.
     */
    @FXML
    private void onExitClicked() {
        System.exit(0);
    }
}