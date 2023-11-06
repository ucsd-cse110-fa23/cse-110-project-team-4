package client;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class DetailedViewController implements ViewController{
    Stage dvcStage;
    DetailedViewController(Stage primaryStage){
        this.dvcStage = primaryStage;
    }

    public void display(){
        DetailedRecipeView drv = new DetailedRecipeView();
        dvcStage.setScene(new Scene(drv, 1000, 600));
        dvcStage.setTitle("PantryPal");
        dvcStage.show();
    }

    public void closeDisplay() {
        dvcStage.close();
    }
}
