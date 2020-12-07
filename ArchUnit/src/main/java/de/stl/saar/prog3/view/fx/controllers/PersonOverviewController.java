package de.stl.saar.prog3.view.fx.controllers;

import java.util.List;

import de.stl.saar.prog3.constants.StringConstants;
import de.stl.saar.prog3.facades.ServiceFacade;
import de.stl.saar.prog3.factories.FxModelFactory;
import de.stl.saar.prog3.i18n.I18nMessagesUtil;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.model.interfaces.Person;
import de.stl.saar.prog3.utils.FXUtils;
import de.stl.saar.prog3.view.fx.FxSceneManager;
import de.stl.saar.prog3.view.fx.model.EquipmentFx;
import de.stl.saar.prog3.view.fx.model.PersonFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
	@FXML
    private TableView<PersonFx> tblPersons;
    @FXML
    private TableColumn<PersonFx, String> colFirstName;
    @FXML
    private TableColumn<PersonFx, String> colLastName;

    @FXML
    private Label lblCompany;
    @FXML
    private Label lblFirstName;
    @FXML
    private Label lblLastName;
    @FXML
    private Label lblPersonId;
    @FXML
    private ListView<EquipmentFx> lstEquipment;
    
    private ObservableList<PersonFx> personListFx; 
    
    private ServiceFacade serviceFacade;
    
    private FxSceneManager sceneManager;
   /**
     * Der Konstruktor wird vor der Methode initialize() aufgerufen
     * und sollte daher im Idealfall keinen Code enthalten oder nur
     * Code, der nicht auf JavaFX-Elemente zugreift. Diese existieren
     * naemlich erst in initialize().
     */
    public PersonOverviewController() {
	}
    
    /**
     * Initialisiert die Controller-Klasse und wird automatisch nach dem Laden der
     * fxml-Datei aufgerufen.
     */
    @FXML
    private void initialize() {
    	serviceFacade = ServiceFacade.getInstance();
    	initializeTable();
    	
    }
    
    private ObservableList<PersonFx> createObversableListWithPersons() {
    	final List<Person> persons = serviceFacade.findAllPersons(); 
    	
    	final List<PersonFx> personsFx = FxModelFactory.createPersonFxList(persons);
    	personListFx = FXCollections.observableArrayList(personsFx);
    	return personListFx;
    }
    
    private void initializeTable() {
    	final ObservableList<PersonFx> personsList = createObversableListWithPersons();
    	tblPersons.setItems(personsList);
        // Initialisiert die Tabelle mit zwei Spalten.
        colFirstName.setCellValueFactory(cellData -> 
        		cellData.getValue().firstNameProperty());
        colLastName.setCellValueFactory(cellData -> 
        		cellData.getValue().lastNameProperty());
        
        resetPersonDetails();
        // Lauscht auf die Aenderung der Auswahl und aktualisiert die Details.
        tblPersons.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> 
                	showPersonDetails(newValue));
    }
    
    /**
     * Fuellt alle Textfelder mit Detaildaten ueber die Person. Wenn keine
     * Person ausgewaehlt wurde, werden alle Felder geleert.
     * @param person Die Person oder null.
     */
    private void showPersonDetails(PersonFx person) {
        if (person != null) { 
        	final long personId = person.getPersonId();
            lblPersonId.setText(String.valueOf(personId));
            lblFirstName.setText(person.getFirstName());
            lblLastName.setText(person.getLastName());
            
            showCompanyDetails(person);
            showEquipmentDetails(person);
        } 
    }
    
    private void showCompanyDetails(final PersonFx person) {
    	final Company company = person.getCompany();
        if (company != null) {
        	lblCompany.setText(person.getCompany().getCompanyName());
        }    	
    }
    
    private void showEquipmentDetails(final PersonFx person) {
    	final List<Equipment> equipment = person.getEquipment();
        lstEquipment.getItems().clear();
        equipment.forEach(selectedEquipment -> {
        	final EquipmentFx equipmentFx = FxModelFactory.createEquipmentFx(selectedEquipment);
        	lstEquipment.getItems().add(equipmentFx);
        });
    }
    
    /**
     * Zeigt einen Dialog fuer die Eingabe einer neuen Person an, wenn der Benutzer
     * auf die Neu-Schaltflaeche klickt.
     */
    @FXML
    private void onNewPersonClicked() {
        PersonFx newFxPerson = FxModelFactory.createPersonFx();
        boolean okClicked = sceneManager.showPersonEditDialog(newFxPerson);
        if (okClicked) {
        	personListFx.add(newFxPerson);
        	final Person newPerson = newFxPerson.getPerson();
        	serviceFacade.saveNewPerson(newPerson);
        	final long newPersonId = newFxPerson.getPerson().getPersonId();
        	newFxPerson.setPersonId(newPersonId);
        }
    }
    
    /**                                                                                                             
     * Zeigt einen Dialog zum Aendern einer Person an, wenn der Benutzer
     * auf die Aendern-Schaltflaeche klickt.
     */
    @FXML
    private void onEditPersonClicked() {
        final PersonFx selectedPerson = tblPersons.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = sceneManager.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
            }
        } else {
        	FXUtils.showWarningDialog(I18nMessagesUtil.getErrorNoSelectionString(), 
        			I18nMessagesUtil.getErrorNoPersonSelectionString(), 
        			I18nMessagesUtil.getMessagePleaseSelectPersonString());
        }
    }
    
    /**
     * Wird aufgerufen, wenn der Benutzer eine Person loeschen moechte.
     */
    @FXML
    private void onDeletePersonClicked() {
        int selectedIndex = tblPersons.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	tblPersons.getItems().remove(selectedIndex);
        } else {
        	FXUtils.showWarningDialog(I18nMessagesUtil.getErrorNoSelectionString(), 
        			I18nMessagesUtil.getErrorNoPersonSelectionString(), 
        			I18nMessagesUtil.getMessagePleaseSelectPersonString());
        }
    }
    
    private void resetPersonDetails() {
    	lblFirstName.setText(StringConstants.EMPTY_STRING);
        lblLastName.setText(StringConstants.EMPTY_STRING);
        lblCompany.setText(StringConstants.EMPTY_STRING);
        lblPersonId.setText(StringConstants.EMPTY_STRING);
    }
    
    public void setSceneManager(FxSceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
}