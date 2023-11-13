package client;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class DetailedViewController implements ViewController{
    Stage dvcStage;
    int screenSizeWidth;
    int screenSizeHeight;
    DetailedRecipeView drv;
    MainViewController mc;
    DetailedViewController(Stage primaryStage,int screenSizeWidth,int screenSizeHeight,MainViewController mc){
        this.mc = mc;
        this.dvcStage = primaryStage;
        this.screenSizeWidth = screenSizeWidth;
        this.screenSizeHeight = screenSizeHeight;
        this.drv = new DetailedRecipeView(this);
        dvcStage.setScene(new Scene(drv, this.screenSizeWidth, this.screenSizeHeight));
        dvcStage.setTitle("PantryPal");
    }

    public void display(){
        this.drv.getDetailedRecipeInfoBody().textFieldEditable(false);
        dvcStage.show();
    }

    public void display(String uuid){
        this.drv.getDetailedRecipeInfoBody().textFieldEditable(false);
        drv.getAndSetInfo(uuid);
        dvcStage.show();
    }

    public void closeDisplay() {
        dvcStage.close();
    }
}
