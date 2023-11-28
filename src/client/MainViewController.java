package client;

import client.FactoryForDetailedViews.AbstractFactoryForDetailedView;
import client.FactoryForDetailedViews.FDRV1;
import client.FactoryForDetailedViews.FDRV2;
import javafx.stage.Stage;

public class MainViewController {
    Stage mpvcStage = new Stage();
    Stage drvStage1 = new Stage();
    Stage drvStage2 = new Stage();
    Stage grvStage = new Stage();
    final int screenSizeWidth = 500;
    final int screenSizeHeight = 700;

    MultipleRecipeViewController mpvc;
    DetailedViewController dvc1;
    DetailedViewController dvc2;
    GenerateRecipesViewController grvc;
    AbstractFactoryForDetailedView<DetailedViewController> affdv1 = new FDRV1();
    AbstractFactoryForDetailedView<DetailedViewController> affdv2 = new FDRV2();

    MainViewController() {
        // Set the title of the app

        this.mpvc = new MultipleRecipeViewController(mpvcStage,screenSizeWidth,screenSizeHeight,this);
        this.dvc1 = new DetailedViewController(drvStage1,screenSizeWidth,screenSizeHeight,this,affdv1);
        this.dvc2 = new DetailedViewController(drvStage2,screenSizeWidth,screenSizeHeight,this,affdv2);
        this.grvc = new GenerateRecipesViewController(grvStage, screenSizeWidth,screenSizeHeight, this);
        
        //
        //grvc.display();
        mpvc.display();
        //dvc1.display();
        //dvc2.display();
        //dvc.display();
    }

    public void closeGenerateOpenDetailed() {
        this.grvc.closeDisplay();
        this.dvc2.display();
    }

    public void closeMultipleOpenGenerate() {
        this.mpvc.closeDisplay();
        this.grvc.display();
    }

    public void closeDetailedOpenMultiple() {
        this.dvc1.closeDisplay();
        this.dvc2.closeDisplay();
        this.mpvc.display();
    }

    public void closeMultipleOpenDetailed(String uuid){
        this.mpvc.closeDisplay();
        this.dvc1.display(uuid);
    }

    public DetailedViewController getNewDetailedViewController() {
        return this.dvc2;
    }
}
