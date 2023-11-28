package client;

import com.mongodb.Function;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class MultipleRecipeViewController implements ViewController{
    Stage mrvcStage;
    int screenSizeWidth;
    int screenSizeHeight;
    MainViewController mvc;
    MultipleRecipeView mrv;
    MultipleRecipeViewController(Stage primaryStage,int screenSizeWidth,int screenSizeHeight, MainViewController mvc){
        this.mrvcStage = primaryStage;
        this.screenSizeWidth = screenSizeWidth;
        this.screenSizeHeight = screenSizeHeight;
        this.mvc = mvc;
        this.mrv = new MultipleRecipeView(this);
        mrvcStage.setScene(new Scene(mrv,this.screenSizeWidth, this.screenSizeHeight));
        mrvcStage.setTitle("PantryPal");
    }

    public void display(){
        
        if (this.mrv.loadRecipeList()) {
            mrvcStage.show();
        }
        else{
            mrvcStage.show();
            ServerErrorNotification.alertNoConn();
            this.display();
        }
        
    }

    public void closeDisplay(){
        mrvcStage.close();
    }

    public void transitionToDetailed(String uuid) {
        this.mvc.closeMultipleOpenDetailed(uuid);
    }

    public void transitionToGenerate() {
        this.mvc.closeMultipleOpenGenerate();
    }
}
