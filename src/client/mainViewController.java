package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class mainViewController {
    Stage mpvcStage = new Stage();
    Stage drvStage = new Stage();
    mainViewController() {
        // Set the title of the app

        MultipleRecipeViewController mpv = new MultipleRecipeViewController(mpvcStage);
        DetailedViewController dvc = new DetailedViewController(drvStage);
        dvc.display();

        mpv.display();
        
    }
}