package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GenerateRecipesViewController implements ViewController{
    Stage grvcStage;
    GenerateRecipesViewController(Stage primaryStage){
        this.grvcStage = primaryStage;
    }

    public void display(){
        GenerateRecipesView grv = new GenerateRecipesView();
        grvcStage.setScene(new Scene(grv, 1000, 600));
        grvcStage.setTitle("PantryPal");
        grvcStage.show();
    }
}