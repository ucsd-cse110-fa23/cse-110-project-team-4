package client;

import java.util.Arrays;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class DetailedNewRecipeView extends DetailedRecipeView {
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
            newReloadedRecipe = this.performReloadButtonAction();
            super.getDetailedRecipeInfoBody().setRecipeNAme(newReloadedRecipe[0]);
            super.getDetailedRecipeInfoBody().setRecipeContext(newReloadedRecipe[1]);
            reloadButton.setVisible(true);
            
        });
    }

    public String[] performReloadButtonAction() {
        
        try {
            at = new AudioTranscriber("src/client/audio/RecordAudio.wav");
            this.prompt = at.generateTranscription();
            GenerateRecipeHandler grh = new GenerateRecipeHandler(
                    "Give me a recipe with no semicolons or commas" + this.prompt +
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



}

