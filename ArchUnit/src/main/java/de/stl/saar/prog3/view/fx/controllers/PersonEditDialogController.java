package de.stl.saar.prog3.view.fx.controllers;

import java.util.List;

import de.stl.saar.prog3.facades.ServiceFacade;
import de.stl.saar.prog3.factories.FxModelFactory;
import de.stl.saar.prog3.i18n.I18nMessagesUtil;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.utils.FXUtils;
import de.stl.saar.prog3.validators.PersonValidator;
import de.stl.saar.prog3.validators.StringValidator;
import de.stl.saar.prog3.view.fx.model.CompanyFx;
import de.stl.saar.prog3.view.fx.model.EquipmentFx;
import de.stl.saar.prog3.view.fx.model.PersonFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonEditDialogController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private ListView<EquipmentFx> lstEquipment;
    @FXML
    private ComboBox<CompanyFx> cmbCompany;
    //private ObservableList<CompanyFx> companyData;
    private ServiceFacade serviceFacade;
    

    private Stage dialogStage;
    private PersonFx person;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    	serviceFacade = ServiceFacade.getInstance();
    	initializeCompaniesComboBox();
    	initializeEquipmentListView();
    }
    
    private void initializeCompaniesComboBox() {
    	final ObservableList<CompanyFx> companyData = FXCollections.observableArrayList();
    	final List<Company> companies = serviceFacade.findAllCompanies();
    	final List<CompanyFx> companiesFx = FxModelFactory.createCompanyFxList(companies);
    	companyData.addAll(companiesFx);
    	cmbCompany.setItems(companyData);
    	cmbCompany.setOnAction(event -> {
    		final CompanyFx selectedCompany = cmbCompany.getSelectionModel().getSelectedItem();
    		final Company company = selectedCompany.getCompany();
    		person.setCompany(company);
    	});
    }
    
    private void initializeEquipmentListView() {
    	final ObservableList<EquipmentFx> equipmentData = FXCollections.observableArrayList();
    	final List<Equipment> equipment = serviceFacade.findAllEquipment();
    	final List<EquipmentFx> equipementFx = FxModelFactory.createEquipmentFxList(equipment);
    	lstEquipment.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    	equipmentData.addAll(equipementFx);
    	lstEquipment.setItems(equipmentData);
    	lstEquipment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
    		final Equipment selectedEquipment = newValue.getEquipment();
    		person.addEquipment(selectedEquipment);
    	});
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
     * Setzt die Person, die in dem Dialog bearbeitet werden soll.
     * 
     * @param person
     */
    public void setPerson(PersonFx person) {
        this.person = person;

        firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
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
     * in der Person und schliesst den Dialog. Ausserdem wird okClicked auf true gesetzt,
     * damit das aufrufende Fenster erkennen kann, dass der Benutzer den Dialog 
     * ordnungsgemaess geschlossen hat.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());

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
        String errorMessage = validatePerson();

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
     * Validiert die Eingabefelder fuer die Eingabe einer Person.
     * @return Fehlermeldungen, wenn Validierungsfehler aufgetreten sind, oder ein
     * leerer String.
     */
    private String validatePerson() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String errorMessage = "";
        
        if (PersonValidator.isFirstNameEmpty(firstName)) {
            errorMessage += I18nMessagesUtil.getErrorNoValidFirstnameString() + "\n"; 
        }
        if (PersonValidator.isLastNameEmpty(lastName)) {
        	errorMessage += I18nMessagesUtil.getErrorNoValidLastnameString() + "\n"; 
        }
        
        return errorMessage;
    }
}
