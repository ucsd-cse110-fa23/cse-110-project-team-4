package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainViewController {
    Stage mpvcStage = new Stage();
    Stage drvStage = new Stage();
    Stage grvStage = new Stage();
    final int screenSizeWidth = 500;
    final int screenSizeHeight = 750;

    MainViewController() {
        // Set the title of the app

        MultipleRecipeViewController mpv = new MultipleRecipeViewController(mpvcStage,screenSizeWidth,screenSizeHeight);
        DetailedViewController dvc = new DetailedViewController(drvStage,screenSizeWidth,screenSizeHeight);
        GenerateRecipesViewController grvc = new GenerateRecipesViewController(grvStage, screenSizeWidth,screenSizeHeight);
        //
                grvc.display();
        //mpv.display();
        dvc.display();
    }
}
