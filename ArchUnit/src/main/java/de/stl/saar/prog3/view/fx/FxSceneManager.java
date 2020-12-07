package de.stl.saar.prog3.view.fx;

import java.io.IOException;
import java.util.ResourceBundle;

import de.stl.saar.prog3.constants.FXConstants;
import de.stl.saar.prog3.i18n.I18nUtil;
import de.stl.saar.prog3.utils.FXUtils;
import de.stl.saar.prog3.view.fx.controllers.CompanyEditDialogController;
import de.stl.saar.prog3.view.fx.controllers.CompanyOverviewController;
import de.stl.saar.prog3.view.fx.controllers.EquipmentEditDialogController;
import de.stl.saar.prog3.view.fx.controllers.EquipmentOverviewController;
import de.stl.saar.prog3.view.fx.controllers.PersonEditDialogController;
import de.stl.saar.prog3.view.fx.controllers.PersonOverviewController;
import de.stl.saar.prog3.view.fx.controllers.RootLayoutController;
import de.stl.saar.prog3.view.fx.model.CompanyFx;
import de.stl.saar.prog3.view.fx.model.EquipmentFx;
import de.stl.saar.prog3.view.fx.model.PersonFx;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FxSceneManager {
	private BorderPane rootLayout;
	private Stage primaryStage;
	
	private static final int TAB_INDEX_PERSON_OVERVIEW = 0;
	private static final int TAB_INDEX_COMPANY_OVERVIEW = 2;
	private static final int TAB_INDEX_EQUIPMENT_OVERVIEW = 1;
	
	/**
     * Initialisiert das RootLayout mit dem Grundgeruest der Anwendung.
     */
    public void initRootLayout(final Stage primaryStage) {
        try {
        	this.primaryStage = primaryStage;
            final FXMLLoader loader = new FXMLLoader();
            final ResourceBundle bundle = I18nUtil.getMessagesResourceBundle();
            loader.setLocation(FxSceneManager.class
                    .getResource(FXConstants.PATH_ROOT_WINDOW));
            loader.setResources(bundle);
            rootLayout = (BorderPane)loader.load();
            final Scene scene = new Scene(rootLayout);
            this.primaryStage.setScene(scene);
            final RootLayoutController controller = loader.getController();
            controller.setSceneManager(this);
            showPersonOverview();
            showCompanyOverview();
            showEquipmentOverview();
            this.primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Zeigt die Personenuebersicht an.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
        	final FXMLLoader loader = new FXMLLoader();
        	final ResourceBundle bundle = I18nUtil.getMessagesResourceBundle();
            loader.setLocation(FxSceneManager.class
                    .getResource(FXConstants.PATH_PERSON_OVERVIEW));
            loader.setResources(bundle);
            final AnchorPane personOverview = (AnchorPane) loader.load();

            final TabPane tabPane = (TabPane)rootLayout.getCenter();
            final Tab personTab = tabPane.getTabs().get(TAB_INDEX_PERSON_OVERVIEW);
            personTab.setContent(personOverview);

            final PersonOverviewController controller = loader.getController();
            controller.setSceneManager(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showCompanyOverview() {
        try {
        	final FXMLLoader loader = new FXMLLoader();
        	final ResourceBundle bundle = I18nUtil.getMessagesResourceBundle();
            loader.setLocation(FxSceneManager.class
                    .getResource(FXConstants.PATH_COMPANY_OVERVIEW));
            loader.setResources(bundle);
            final AnchorPane companyOverview = (AnchorPane) loader.load();

            final TabPane tabPane = (TabPane)rootLayout.getCenter();
            final Tab companyTab = tabPane.getTabs().get(TAB_INDEX_COMPANY_OVERVIEW);
            companyTab.setContent(companyOverview);

            final CompanyOverviewController controller = loader.getController();
            controller.setSceneManager(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showEquipmentOverview() {
        try {
        	final FXMLLoader loader = new FXMLLoader();
        	final ResourceBundle bundle = I18nUtil.getMessagesResourceBundle();
            loader.setLocation(FxSceneManager.class
                    .getResource(FXConstants.PATH_EQUIPMENT_OVERVIEW));
            loader.setResources(bundle);
            AnchorPane equipmentOverview = (AnchorPane) loader.load();

            final TabPane tabPane = (TabPane)rootLayout.getCenter();
            final Tab equipmentTab = tabPane.getTabs().get(TAB_INDEX_EQUIPMENT_OVERVIEW);
            equipmentTab.setContent(equipmentOverview);

            final EquipmentOverviewController controller = loader.getController();
            controller.setSceneManager(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Oeffnet einen Dialog, um eine Person zu aendern oder neu anzulegen.
     * Wenn der Benutzer Ok klickt, werden die Aenderungen in der Liste 
     * gespeichert und true wird zurueckgegeben. 
     * 
     * @param person Die Person, die bearbeitet werden soll.
     * @return true, wenn der Benutzer OK geklickt hat, sonst false.
     */
    public boolean showPersonEditDialog(final PersonFx person) {
        try {
        	final FXMLLoader loader = new FXMLLoader();
        	final ResourceBundle bundle = I18nUtil.getMessagesResourceBundle();
            loader.setLocation(FxSceneManager.class
                    .getResource(FXConstants.PATH_PERSON_EDIT_DIALOG));
            loader.setResources(bundle);
            final AnchorPane page = (AnchorPane) loader.load();

            final Stage dialogStage = new Stage();
            final String title = FXUtils.getDialogTitle("");
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            final Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Setzt die Person in den Controller.
            final PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            
            //dialogStage.getIcons().add(new Image(FXConstants.PATH_EDIT_ICON));
            // Zeigt den Dialog und wartet, bis der Benutzer ihn schliesst.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean showCompanyEditDialog(final CompanyFx company) {
        try {
        	final FXMLLoader loader = new FXMLLoader();
        	final ResourceBundle bundle = I18nUtil.getMessagesResourceBundle();
            loader.setLocation(FxSceneManager.class
                    .getResource(FXConstants.PATH_COMPANY_EDIT_DIALOG));
            loader.setResources(bundle);
            final AnchorPane page = (AnchorPane) loader.load();

            final Stage dialogStage = new Stage();
            final String title = FXUtils.getDialogTitle("");
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            final Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            final CompanyEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCompany(company);
            //dialogStage.getIcons().add(new Image(FXConstants.PATH_EDIT_ICON));
            // Zeigt den Dialog und wartet, bis der Benutzer ihn schliesst.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showEquipmentEditDialog(final EquipmentFx equipment) {
        try {
        	final FXMLLoader loader = new FXMLLoader();
        	final ResourceBundle bundle = I18nUtil.getMessagesResourceBundle();
            loader.setLocation(FxSceneManager.class
                    .getResource(FXConstants.PATH_EQUIPMENT_EDIT_DIALOG));
            loader.setResources(bundle);
            final AnchorPane page = (AnchorPane) loader.load();

            final Stage dialogStage = new Stage();
            final String title = FXUtils.getDialogTitle("");
            dialogStage.setTitle(title);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            final Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            final EquipmentEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setEquipment(equipment);
            //dialogStage.getIcons().add(new Image(FXConstants.PATH_EDIT_ICON));
            // Zeigt den Dialog und wartet, bis der Benutzer ihn schliesst.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
