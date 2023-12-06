package client.multipleView;

import client.MainViewController;
import client.ViewController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import client.ServerErrorNotification;

public class MultipleRecipeViewController implements ViewController{
    Stage mrvcStage;
    int screenSizeWidth;
    int screenSizeHeight;
    MainViewController mvc;
    MultipleRecipeView mrv;
    public MultipleRecipeViewController(Stage primaryStage,int screenSizeWidth,int screenSizeHeight, MainViewController mvc){
        this.mrvcStage = primaryStage;
        this.screenSizeWidth = screenSizeWidth;
        this.screenSizeHeight = screenSizeHeight;
        this.mvc = mvc;
        this.mrv = new MultipleRecipeView(this);
        mrvcStage.setScene(new Scene(mrv,this.screenSizeWidth, this.screenSizeHeight));
        mrvcStage.setTitle("PantryPal");
    }

    public void display(){
        
        if (this.mrv.loadRecipeList()) {
            mrvcStage.show();
        }
        else{
            mrvcStage.show();
            ServerErrorNotification.alertNoConn();
            Platform.exit();
        }
        
    }

    public void closeDisplay(){
        mrvcStage.close();
    }

    public void transitionToDetailed(String uuid) {
        this.mvc.closeMultipleOpenDetailed(uuid);
    }

    public void transitionToGenerate() {
        this.mvc.closeMultipleOpenGenerate();
    }

    public void transitiontoLogin() {
        this.mvc.closeMultipleOpenLogin();
    }
}
