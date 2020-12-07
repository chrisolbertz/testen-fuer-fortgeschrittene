package de.stl.saar.prog3.view.fx.controllers;

import de.stl.saar.prog3.facades.ServiceFacade;
import de.stl.saar.prog3.i18n.I18nMessagesUtil;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.service.interfaces.CompanyService;
import de.stl.saar.prog3.utils.FXUtils;
import de.stl.saar.prog3.validators.CompanyValidator;
import de.stl.saar.prog3.validators.StringValidator;
import de.stl.saar.prog3.view.fx.model.CompanyFx;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CompanyEditDialogController {

    @FXML
    private TextField companyNameField;
    @FXML
    private TextField employeeCountField;

    private Stage dialogStage;
    private CompanyFx company;
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
     * Setzt die Company, die in dem Dialog bearbeitet werden soll.
     * 
     * @param company
     */
    public void setCompany(CompanyFx company) {
        this.company = company;

        companyNameField.setText(company.getCompanyName());
        int employeeCount = company.getEmployeeCount();
        employeeCountField.setText(String.valueOf(employeeCount));
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
     * in der Firma und schliesst den Dialog. Ausserdem wird okClicked auf true gesetzt,
     * damit das aufrufende Fenster erkennen kann, dass der Benutzer den Dialog 
     * ordnungsgemaess geschlossen hat.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            company.setCompanyName(companyNameField.getText());
            final int employeeCount = Integer.parseInt(employeeCountField.getText());
            company.setEmployeeCount(employeeCount);
            final Company newCompany = company.getCompany();            
            serviceFacade.saveNewCompany(newCompany);
            company.setCompanyId(newCompany.getCompanyId());
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
        String errorMessage = validateCompany();

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
     * Validiert die Eingabefelder fuer die Eingabe einer Firma.
     * @return Fehlermeldungen, wenn Validierungsfehler aufgetreten sind, oder ein
     * leerer String.
     */
    private String validateCompany() {
        final String companyName = companyNameField.getText();
        final String employeeCountAsString = employeeCountField.getText();
        String errorMessage = "";
        
        if (CompanyValidator.isCompanyNameEmpty(companyName)) {
            errorMessage += I18nMessagesUtil.getErrorNoValidCompanyNameString() + "\n"; 
        }
        if (CompanyValidator.isEmployeeCountEmpty(employeeCountAsString)) {
        	errorMessage += I18nMessagesUtil.getErrorNoValidEmployeeCountString() + "\n"; 
        }
        
        final int employeeCount = Integer.parseInt(employeeCountAsString);
        if (CompanyValidator.isEmployeeCountInvalid(employeeCount)) {
        	errorMessage += I18nMessagesUtil.getErrorNoValidEmployeeCountString() + "\n";
        }
        
        return errorMessage;
    }
}
