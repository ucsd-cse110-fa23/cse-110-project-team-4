package client;

import client.accountViews.CreateAccountViewController;
import client.accountViews.LoginViewController;
import client.detailedView.DetailedViewController;
import client.factoryForDetailedViews.AbstractFactoryForDetailedView;
import client.factoryForDetailedViews.FDRV1;
import client.factoryForDetailedViews.FDRV2;
import client.generateRecipes.GenerateRecipesViewController;
import client.multipleView.MultipleRecipeViewController;
import javafx.stage.Stage;

public class MainViewController {
    Stage mpvcStage = new Stage();
    Stage drvStage1 = new Stage();
    Stage drvStage2 = new Stage();
    Stage grvStage = new Stage();
    Stage lvcStage = new Stage();
    Stage cavcStage = new Stage();
    final int screenSizeWidth = 500;
    final int screenSizeHeight = 700;

    MultipleRecipeViewController mpvc;
    DetailedViewController dvc1;
    DetailedViewController dvc2;
    GenerateRecipesViewController grvc;
    LoginViewController lvc;
    CreateAccountViewController cavc;
    AbstractFactoryForDetailedView<DetailedViewController> affdv1 = new FDRV1();
    AbstractFactoryForDetailedView<DetailedViewController> affdv2 = new FDRV2();

    String currentUser = "";

    MainViewController() {
        // Set the title of the app

        this.mpvc = new MultipleRecipeViewController(mpvcStage,screenSizeWidth,screenSizeHeight,this);
        this.dvc1 = new DetailedViewController(drvStage1,screenSizeWidth,screenSizeHeight,this,affdv1);
        this.dvc2 = new DetailedViewController(drvStage2,screenSizeWidth,screenSizeHeight,this,affdv2);
        this.grvc = new GenerateRecipesViewController(grvStage, screenSizeWidth,screenSizeHeight, this);
        this.lvc = new LoginViewController(lvcStage, screenSizeWidth, screenSizeHeight, this);
        this.cavc = new CreateAccountViewController(cavcStage, screenSizeWidth, screenSizeHeight, this);
        
        lvc.display();
        //
        //grvc.display();
        //mpvc.display();
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

    public void closeLoginOpenMultiple(){
        this.lvc.closeDisplay();
        this.mpvc.display();
    }

    public void closeCreateOpenLogin(){
        this.cavc.closeDisplay();
        this.lvc.display();
    }

    public void closeLoginOpenCreate(){
        this.lvc.closeDisplay();
        this.cavc.display();
    }

    public void setUser(String user){
        this.currentUser = user;
    }

    public String getUser(){
        return this.currentUser;
    }
}
