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

    MultipleRecipeViewController mpvc;
    DetailedViewController dvc;
    GenerateRecipesViewController grvc;

    MainViewController() {
        // Set the title of the app

        this.mpvc = new MultipleRecipeViewController(mpvcStage,screenSizeWidth,screenSizeHeight,this);
        this.dvc = new DetailedViewController(drvStage,screenSizeWidth,screenSizeHeight);
        this.grvc = new GenerateRecipesViewController(grvStage, screenSizeWidth,screenSizeHeight, this);
        //
        //grvc.display();
        mpvc.display();
        
        // dvc.display();
    }

    public void closeGenerateOpenDetailed() {
        this.grvc.closeDisplay();
        this.dvc.display();
    }

    public void closeMultipleOpenGenerate() {
        this.mpvc.closeDisplay();
        this.grvc.display();
    }
}
