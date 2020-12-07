package de.stl.saar.prog3.main;

import de.stl.saar.prog3.view.fx.FxSceneManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class FxMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
    	final FxSceneManager sceneManager = new FxSceneManager();
    	sceneManager.initRootLayout(primaryStage);
    }

    public static void main(String[] args) {
        launch();
    }
}
