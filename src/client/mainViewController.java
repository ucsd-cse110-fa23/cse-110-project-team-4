package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainViewController {
    Stage mpvcStage = new Stage();
    Stage drvStage = new Stage();
    MainViewController() {
        // Set the title of the app

        MultipleRecipeViewController mpv = new MultipleRecipeViewController(mpvcStage);
        DetailedViewController dvc = new DetailedViewController(drvStage);
        dvc.display();

        mpv.display();
        
    }
}