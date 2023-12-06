package client.detailedView;

import java.util.Arrays;

import client.openAIAPI.AudioTranscriber;
import client.generateRecipes.GenerateRecipeHandler;
import client.openAIAPI.DallEImageGenerator;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import client.ServerErrorNotification;

public class DetailedNewRecipeView extends DetailedRecipeViewTemplate {
    Button reloadButton = new Button("Reload");

    private AudioTranscriber at;
    private String prompt;
    private String[] newReloadedRecipe;
    public DetailedNewRecipeView(DetailedViewController vc) {
        super(vc);
        this.addNewListeners();
        ((HBox) super.header.getChildren().get(2)).getChildren().add(reloadButton);
    }

    public void addNewListeners() {

        reloadButton.setOnAction(e -> {
            reloadButton.setVisible(false);
            newReloadedRecipe = this.performReloadButtonActionRecipe();
            byte[] image = null;
            if(newReloadedRecipe!=null){
                image = performReloadButtonActionImage(newReloadedRecipe[0]);
            }
            if(image!=null){
                super.getDetailedRecipeInfoBody().setImage(image);
                super.getDetailedRecipeInfoBody().setRecipeNAme(newReloadedRecipe[0]);
                super.getDetailedRecipeInfoBody().setRecipeContext(newReloadedRecipe[1]);
            }
            else{
                ServerErrorNotification.alertNoConn();
            }
            reloadButton.setVisible(true);

        });
    }

    public String[] performReloadButtonActionRecipe() {

        try {
            at = new AudioTranscriber("src/client/audio/RecordAudio.wav");
            this.prompt = at.generateTranscription();
            GenerateRecipeHandler grh = new GenerateRecipeHandler(
                    this.prompt +
                            "in the format of title followed by ingredients, and then instructions for a recipe.");
            String recipeInfo = grh.makeRequest();
            String[] recipeInfoSplit = recipeInfo.split("\n");
            String recipeName = recipeInfoSplit[2];
            String recipeDetails = String.join("\n",
                    Arrays.copyOfRange(recipeInfoSplit, 3, recipeInfoSplit.length));
            String[] toReturn = { "", "" };
            toReturn[0] = recipeName;
            toReturn[1] = recipeDetails;
            return toReturn;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public byte[] performReloadButtonActionImage(String recipeName){
        
        DallEImageGenerator dig = new DallEImageGenerator(recipeName);
        byte[] imageArray =null;
        try {
            imageArray = dig.generateImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageArray;
    }

}