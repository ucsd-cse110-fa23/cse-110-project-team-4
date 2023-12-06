package client.detailedView;

import client.MainViewController;
import client.ViewController;
import client.FactoryForDetailedViews.AbstractFactoryForDetailedView;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DetailedViewController implements ViewController{
    Stage dvcStage;
    int screenSizeWidth;
    int screenSizeHeight;
    DView drv;
    MainViewController mc;
    AbstractFactoryForDetailedView<DetailedViewController> affdv;
    public DetailedViewController(Stage primaryStage,int screenSizeWidth,int screenSizeHeight,MainViewController mc, AbstractFactoryForDetailedView<DetailedViewController> affdv){
        this.mc = mc;
        this.dvcStage = primaryStage;
        this.screenSizeWidth = screenSizeWidth;
        this.screenSizeHeight = screenSizeHeight;
        this.affdv = affdv;
        this.makeDetailedView();
        dvcStage.setScene(new Scene((BorderPane)drv, this.screenSizeWidth, this.screenSizeHeight));
        dvcStage.setTitle("PantryPal");
    }
    public void makeDetailedView(){
        drv = affdv.getView(this);
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
