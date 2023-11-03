package client;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class DetailedViewController implements viewController{
    Stage dvcStage;
    DetailedViewController(Stage primaryStage){
        this.dvcStage = primaryStage;
    }

    public void display(){
        DetailedRecipeView mrv = new DetailedRecipeView();
        dvcStage.setScene(new Scene(mrv, 1000, 600));
        dvcStage.setTitle("PantryPal");
        dvcStage.show();
    }
}
