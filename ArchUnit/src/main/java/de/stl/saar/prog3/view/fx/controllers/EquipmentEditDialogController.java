package de.stl.saar.prog3.view.fx.controllers;

import de.stl.saar.prog3.facades.ServiceFacade;
import de.stl.saar.prog3.i18n.I18nMessagesUtil;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.utils.FXUtils;
import de.stl.saar.prog3.validators.EquipmentValidator;
import de.stl.saar.prog3.validators.StringValidator;
import de.stl.saar.prog3.view.fx.model.EquipmentFx;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EquipmentEditDialogController {

    @FXML
    private TextField equipmentNameField;
    @FXML
    private TextField weightField;

    private Stage dialogStage;
    private EquipmentFx equipment;
    private boolean okClicked = false;
    private ServiceFacade serviceFacade;

    @FXML
    private void initialize() {
    	serviceFacade = ServiceFacade.getInstance();
    }

    /**
     * Setzt die Stage fuer diesen Dialog und erzeugt ein Icon.
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        
        //this.dialogStage.getIcons().add(new Image(FXConstants.PATH_EDIT_ICON));
    }

    /**
     * Setzt die Ausrüstung, die in dem Dialog bearbeitet werden soll.
     * 
     * @param equipment
     */
    public void setEquipment(final EquipmentFx equipment) {
        this.equipment = equipment;

        equipmentNameField.setText(equipment.getEquipmentName());
        final double weight = equipment.getWeight();
        weightField.setText(String.valueOf(weight));
    }

    /**
     * Gibt an, ob der Benutzer die Ok-Schaltflaeche gedrueckt hat.
     * 
     * @return true, wenn der Benutzer die Ok-Schaltflaeche gedrueckt hat, sonst false.
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Wird aufgerufen, wenn der Benutzer speichern moechte. Setzt die eingegebenen Daten
     * in der Ausruestung und schliesst den Dialog. Ausserdem wird okClicked auf true gesetzt,
     * damit das aufrufende Fenster erkennen kann, dass der Benutzer den Dialog 
     * ordnungsgemaess geschlossen hat.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            equipment.setEquipmentName(equipmentNameField.getText());
            final double weight = Double.parseDouble(weightField.getText());
            equipment.setWeight(weight);
            final Equipment newEquipment = equipment.getEquipment();            
            serviceFacade.saveNewEquipment(newEquipment);
            equipment.setEquipmentId(newEquipment.getEquipmentId());
            System.out.println(newEquipment);
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Schliesst den Dialog, wenn der Benutzer abbrechen moechte. Die eingegebenen Daten
     * werden nicht uebernommen.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validiert die eingegebenen Daten.
     * 
     * @return true wenn die Eingabe gueltig ist, sonst false.
     */
    private boolean isInputValid() {
        String errorMessage = validateEquipment();

        if (!StringValidator.hasContent(errorMessage)) {
            return true;
        } else {
        	FXUtils.showErrorDialog(I18nMessagesUtil.
        						getErrorInvalidFieldsString(), 
        			I18nMessagesUtil.getMessageCorrectInvalidFieldsString(), 
        				errorMessage);
            
            return false;
        }
    }
    
    /**
     * Validiert die Eingabefelder fuer die Eingabe einer Ausruestung.
     * @return Fehlermeldungen, wenn Validierungsfehler aufgetreten sind, oder ein
     * leerer String.
     */
    private String validateEquipment() {
        final String equipmentName = equipmentNameField.getText();
        final String weightAsString = weightField.getText();
        String errorMessage = "";
        
        if (EquipmentValidator.isEquipmentNameEmpty(equipmentName)) {
            errorMessage += I18nMessagesUtil.getErrorNoValidEquipmentNameString() + "\n"; 
        }
        if (EquipmentValidator.isWeightEmpty(weightAsString)) {
        	errorMessage += I18nMessagesUtil.getErrorNoValidWeightString() + "\n"; 
        }
        
        final double weight = Double.parseDouble(weightAsString);
        if (EquipmentValidator.isWeightInvalid(weight)) {
        	errorMessage += I18nMessagesUtil.getErrorNoValidWeightString() + "\n";
        }
        
        return errorMessage;
    }
}
