package de.stl.saar.prog3.view.fx.controllers;

import java.util.List;

import de.stl.saar.prog3.constants.StringConstants;
import de.stl.saar.prog3.facades.ServiceFacade;
import de.stl.saar.prog3.factories.FxModelFactory;
import de.stl.saar.prog3.i18n.I18nMessagesUtil;
import de.stl.saar.prog3.model.interfaces.Company;
import de.stl.saar.prog3.utils.FXUtils;
import de.stl.saar.prog3.view.fx.FxSceneManager;
import de.stl.saar.prog3.view.fx.model.CompanyFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
 
public class CompanyOverviewController {
	@FXML
    private TableView<CompanyFx> tblCompanies;
	@FXML
	private TableColumn<CompanyFx, Number> colCompanyId;
    @FXML
    private TableColumn<CompanyFx, String> colCompanyName;
    @FXML
    private TableColumn<CompanyFx, Number> colEmployeesCount;
    @FXML
    private Label lblCompanyId;
    @FXML
    private Label lblCompanyName;
    @FXML
    private Label lblEmployeesCount;
    
    private ObservableList<CompanyFx> companyListFx; 
    
    private ServiceFacade serviceFacade;
    
    private FxSceneManager sceneManager;
   /**
     * Der Konstruktor wird vor der Methode initialize() aufgerufen
     * und sollte daher im Idealfall keinen Code enthalten oder nur
     * Code, der nicht auf JavaFX-Elemente zugreift. Diese existieren
     * naemlich erst in initialize().l.saar.prog3.i18n.I18nMessagesUtil;
import src.main.java.de.s
     */
    public CompanyOverviewController() {
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
    
    private ObservableList<CompanyFx> createObversableListWithCompanys() {
    	final List<Company> companies = serviceFacade.findAllCompanies(); 
    	final List<CompanyFx> companiesFx = FxModelFactory.createCompanyFxList(companies);
    	companyListFx = FXCollections.observableArrayList(companiesFx);
    	return companyListFx;
    }
    
    private void initializeTable() {
    	final ObservableList<CompanyFx> companyList = createObversableListWithCompanys();
    	tblCompanies.setItems(companyList);
        // Initialisiert die Tabelle mit zwei Spalten.
    	colCompanyId.setCellValueFactory(cellData -> 
			cellData.getValue().companyIdProperty());
        colCompanyName.setCellValueFactory(cellData -> 
        		cellData.getValue().companyNameProperty());
        colEmployeesCount.setCellValueFactory(cellData -> 
        		cellData.getValue().employeeCountProperty());
        
        resetCompanyDetails();
        // Lauscht auf die Aenderung der Auswahl und aktualisiert die Details.
        tblCompanies.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> 
                	showCompanyDetails(newValue));
    }
    
    /**
     * Fuellt alle Textfelder mit Detaildaten ueber die Company. Wenn keine
     * Company ausgewaehlt wurde, werden alle Felder geleert.
     * ^^^^^
     * @param company Die Company oder null.
     */
    private void showCompanyDetails(CompanyFx company) {
        if (company != null) {
        	final long companyId = company.getCompanyId();
            lblCompanyId.setText(String.valueOf(companyId));
        	lblCompanyName.setText(company.getCompanyName());
            final int employeeCount = company.getEmployeeCount();
            lblEmployeesCount.setText(String.valueOf(employeeCount));
        } 
    }
    
    /**
     * Zeigt einen Dialog fuer die Eingabe einer neuen Company an, wenn der Benutzer
     * auf die Neu-Schaltflaeche klickt.
     */
    @FXML
    private void onNewCompanyClicked() {
        CompanyFx newCompany = FxModelFactory.createCompanyFx();
        boolean okClicked = sceneManager.showCompanyEditDialog(newCompany);
        if (okClicked) {
        	companyListFx.add(newCompany);
        }
    }
    
    /**
     * Zeigt einen Dialog zum Aendern einer Company an, wenn der Benutzer
     * auf die Aendern-Schaltflaeche klickt.
     */
    @FXML
    private void onEditCompanyClicked() {
        CompanyFx selectedCompany = tblCompanies.getSelectionModel().getSelectedItem();
        if (selectedCompany != null) {
            boolean okClicked = sceneManager.showCompanyEditDialog(selectedCompany);
            if (okClicked) {
                showCompanyDetails(selectedCompany);
            }
        } else {
        	FXUtils.showWarningDialog(I18nMessagesUtil.getErrorNoSelectionString(), 
        			I18nMessagesUtil.getErrorNoCompanySelectionString(), 
        			I18nMessagesUtil.getMessagePleaseSelectCompanyString());
        }
    }
    
    /**
     * Wird aufgerufen, wenn der Benutzer eine Firma loeschen moechte.
     */
    @FXML
    private void onDeleteCompanyClicked() {
        int selectedIndex = tblCompanies.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	tblCompanies.getItems().remove(selectedIndex);
        } else {
        	FXUtils.showWarningDialog(I18nMessagesUtil.getErrorNoSelectionString(), 
        			I18nMessagesUtil.getErrorNoCompanySelectionString(), 
        			I18nMessagesUtil.getMessagePleaseSelectCompanyString());
        }
    }
    
    private void resetCompanyDetails() {
    	lblCompanyId.setText(StringConstants.EMPTY_STRING);
    	lblCompanyName.setText(StringConstants.EMPTY_STRING);
        lblEmployeesCount.setText(StringConstants.EMPTY_STRING);
    }
    
    public void setSceneManager(FxSceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
}
