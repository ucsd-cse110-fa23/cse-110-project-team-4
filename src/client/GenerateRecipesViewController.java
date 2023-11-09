package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GenerateRecipesViewController implements ViewController{
    Stage grvcStage;
    int screenSizeWidth;
    int screenSizeHeight;
    MainViewController mvc;
    GenerateRecipesViewController(Stage primaryStage,int screenSizeWidth,int screenSizeHeight, MainViewController mvc){
        this.grvcStage = primaryStage;
        this.screenSizeWidth = screenSizeWidth;
        this.screenSizeHeight = screenSizeHeight;
        this.mvc = mvc;
    }

    public void display(){
        GenerateRecipesView grv = new GenerateRecipesView(this);
        grvcStage.setScene(new Scene(grv, screenSizeWidth, screenSizeHeight));
        grvcStage.setTitle("PantryPal");
        grvcStage.show();
    }

    public void closeDisplay() {
        grvcStage.close();
    }
}