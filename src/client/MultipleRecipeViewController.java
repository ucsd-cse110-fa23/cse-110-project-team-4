package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MultipleRecipeViewController implements ViewController{
    Stage mrvcStage;
    int screenSizeWidth;
    int screenSizeHeight;
    MultipleRecipeViewController(Stage primaryStage,int screenSizeWidth,int screenSizeHeight){
        this.mrvcStage = primaryStage;
        this.screenSizeWidth = screenSizeWidth;
        this.screenSizeHeight = screenSizeHeight;
    }

    public void display(){
        MultipleRecipeView mrv = new MultipleRecipeView();
        mrvcStage.setScene(new Scene(mrv,this.screenSizeWidth, this.screenSizeHeight));
        mrvcStage.setTitle("PantryPal");
        mrvcStage.show();
    }

    public void closeDisplay(){
        mrvcStage.close();
    }
}
