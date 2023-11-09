package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MultipleRecipeViewController implements ViewController{
    Stage mrvcStage;
    int screenSizeWidth;
    int screenSizeHeight;
    MainViewController mvc;
    MultipleRecipeViewController(Stage primaryStage,int screenSizeWidth,int screenSizeHeight, MainViewController mvc){
        this.mrvcStage = primaryStage;
        this.screenSizeWidth = screenSizeWidth;
        this.screenSizeHeight = screenSizeHeight;
        this.mvc = mvc;
        MultipleRecipeView mrv = new MultipleRecipeView(this);
        mrvcStage.setScene(new Scene(mrv,this.screenSizeWidth, this.screenSizeHeight));
        mrvcStage.setTitle("PantryPal");
    }

    public void display(){
        
        mrvcStage.show();
    }

    public void closeDisplay(){
        mrvcStage.close();
    }
}
