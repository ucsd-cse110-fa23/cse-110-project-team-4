package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MultipleRecipeViewController implements ViewController{
    Stage mrvcStage;
    MultipleRecipeViewController(Stage primaryStage){
        this.mrvcStage = primaryStage;
    }

    public void display(){
        MultipleRecipeView mrv = new MultipleRecipeView();
        mrvcStage.setScene(new Scene(mrv, 1000, 600));
        mrvcStage.setTitle("PantryPal");
        mrvcStage.show();
    }
}
