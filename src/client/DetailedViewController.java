package client;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class DetailedViewController implements ViewController{
    Stage dvcStage;
    int screenSizeWidth;
    int screenSizeHeight;
    DetailedViewController(Stage primaryStage,int screenSizeWidth,int screenSizeHeight){
        this.dvcStage = primaryStage;
        this.screenSizeWidth = screenSizeWidth;
        this.screenSizeHeight = screenSizeHeight;
    }

    public void display(){
        DetailedRecipeView drv = new DetailedRecipeView(this);
        //drv.createUIWindow("Ramen", "Step 1,Step 2");
        dvcStage.setScene(new Scene(drv, this.screenSizeWidth, this.screenSizeHeight));
        dvcStage.setTitle("PantryPal");
        dvcStage.show();
    }

    public void closeDisplay(){
        dvcStage.close();
    }


    
}
