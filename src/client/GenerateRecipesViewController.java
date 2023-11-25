package client;

import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Class for opening and closing the Generate Recipes Window as well as transitioning 
 * between it and other windows.
 */

public class GenerateRecipesViewController implements ViewController{
    Stage grvcStage;
    int screenSizeWidth;
    int screenSizeHeight;
    MainViewController mvc;
    GenerateRecipesViewController(Stage primaryStage,int screenSizeWidth,int screenSizeHeight, MainViewController mvc){
        this.grvcStage = primaryStage;
        this.screenSizeWidth = screenSizeWidth;
        this.screenSizeHeight = screenSizeHeight;
        this.mvc = mvc;
    }

    public void display(){
        GenerateRecipesView grv = new GenerateRecipesView(this);
        grvcStage.setScene(new Scene(grv, screenSizeWidth, screenSizeHeight));
        grvcStage.setTitle("PantryPal");
        grvcStage.show();
    }

    public void closeDisplay() {
        grvcStage.close();
    }

    public void exportRecipeToDetailed(String recipeName, String recipeDetails, byte[] imageArray) {
        DetailedViewController dvc = this.mvc.getDetailedViewController();
        DetailedRecipeInfoBody drb = dvc.drv.getDetailedRecipeInfoBody();
        drb.setRecipeNAme(recipeName);
        drb.setRecipeContext(recipeDetails);
        drb.setImage(imageArray);
        drb.setIsNewRecipe(true);
    }

    public void transitionToDetailed() {
        this.mvc.closeGenerateOpenDetailed();
    }
}