package de.stl.saar.prog3.view.fx.controllers;

import java.util.List;

import de.stl.saar.prog3.constants.StringConstants;
import de.stl.saar.prog3.facades.ServiceFacade;
import de.stl.saar.prog3.factories.FxModelFactory;
import de.stl.saar.prog3.i18n.I18nMessagesUtil;
import de.stl.saar.prog3.model.interfaces.Equipment;
import de.stl.saar.prog3.utils.FXUtils;
import de.stl.saar.prog3.view.fx.FxSceneManager;
import de.stl.saar.prog3.view.fx.model.CompanyFx;
import de.stl.saar.prog3.view.fx.model.EquipmentFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
 
public class EquipmentOverviewController {
	@FXML
    private TableView<EquipmentFx> tblEquipment;
    @FXML
    private TableColumn<EquipmentFx, String> colEquipmentName;
    @FXML
    private TableColumn<EquipmentFx, Number> colEquipmentWeight;

    @FXML
    private Label lblEquipmentName;
    @FXML
    private Label lblWeight;
    @FXML
    private Label lblEquipmentId;
    
    private ObservableList<EquipmentFx> equipmentListFx; 
    
    private ServiceFacade serviceFacade;
    
    private FxSceneManager sceneManager;
   /**
     * Der Konstruktor wird vor der Methode initialize() aufgerufen
     * und sollte daher im Idealfall keinen Code enthalten oder nur
     * Code, der nicht auf JavaFX-Elemente zugreift. Diese existieren
     * naemlich erst in initialize().
     */
    public EquipmentOverviewController() {
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
    
    private ObservableList<EquipmentFx> createObversableListWithEquipment() {
    	final List<Equipment> equipments = serviceFacade.findAllEquipment(); 
    	final List<EquipmentFx> equipmentsFx = FxModelFactory.createEquipmentFxList(equipments);
    	equipmentListFx = FXCollections.observableArrayList(equipmentsFx);
    	return equipmentListFx;
    }
    
    private void initializeTable() {
    	final ObservableList<EquipmentFx> equipmentsList = createObversableListWithEquipment();
    	tblEquipment.setItems(equipmentsList);
        // Initialisiert die Tabelle mit zwei Spalten.
        colEquipmentName.setCellValueFactory(cellData -> 
        		cellData.getValue().equipmentNameProperty());
        colEquipmentWeight.setCellValueFactory(cellData -> 
        		cellData.getValue().weightProperty());
        
        resetEquipmentDetails();
        // Lauscht auf die Aenderung der Auswahl und aktualisiert die Details.
        tblEquipment.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> 
                	showEquipmentDetails(newValue));
    }
    
    /**
     * Fuellt alle Textfelder mit Detaildaten ueber die Equipment. Wenn keine
     * Ausruestung ausgewaehlt wurde, werden alle Felder geleert.
     * @param equipment Die Ausruestung oder null.
     */
    private void showEquipmentDetails(EquipmentFx equipment) {
        if (equipment != null) {
        	final long equipmentId = equipment.getEquipmentId();
            lblEquipmentId.setText(String.valueOf(equipmentId));
            lblEquipmentName.setText(equipment.getEquipmentName());
            final String weightAsString = String.valueOf(equipment.getWeight());
            lblWeight.setText(weightAsString);
        } 
    }
    
    /**
     * Zeigt einen Dialog fuer die Eingabe einer neuen Ausrüstung an, wenn der Benutzer
     * auf die Neu-Schaltflaeche klickt.
     */
    @FXML
    private void onNewEquipmentClicked() {
        EquipmentFx newEquipment = FxModelFactory.createEquipmentFx();
        boolean okClicked = sceneManager.showEquipmentEditDialog(newEquipment);
        if (okClicked) {
        	equipmentListFx.add(newEquipment);
        }
    }
    
    /**
     * Zeigt einen Dialog zum Aendern einer Ausruestung an, wenn der Benutzer
     * auf die Aendern-Schaltflaeche klickt.
     */
    @FXML
    private void onEditEquipmentClicked() {
        final EquipmentFx selectedEquipment = tblEquipment.getSelectionModel().getSelectedItem();
        if (selectedEquipment != null) {
            boolean okClicked = sceneManager.showEquipmentEditDialog(selectedEquipment);
            if (okClicked) {
                showEquipmentDetails(selectedEquipment);
            }
        } else {
        	FXUtils.showWarningDialog(I18nMessagesUtil.getErrorNoSelectionString(), 
        			I18nMessagesUtil.getErrorNoEquipmentSelectionString(), 
        			I18nMessagesUtil.getMessagePleaseSelectEquipmentString());
        }
    }
    
    /**
     * Wird aufgerufen, wenn der Benutzer eine Equipment loeschen moechte.
     */
    @FXML
    private void onDeleteEquipmentClicked() {
        int selectedIndex = tblEquipment.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	tblEquipment.getItems().remove(selectedIndex);
        } else {
        	FXUtils.showWarningDialog(I18nMessagesUtil.getErrorNoSelectionString(), 
        			I18nMessagesUtil.getErrorNoEquipmentSelectionString(), 
        			I18nMessagesUtil.getMessagePleaseSelectEquipmentString());
        }
    }
    
    private void resetEquipmentDetails() {
    	lblEquipmentName.setText(StringConstants.EMPTY_STRING);
        lblWeight.setText(StringConstants.EMPTY_STRING);
        lblEquipmentId.setText(StringConstants.EMPTY_STRING);
    }
    
    public void setSceneManager(FxSceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
}
