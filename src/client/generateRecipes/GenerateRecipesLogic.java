package client.generateRecipes;

import java.util.Arrays;

import client.openAIAPI.AudioRecorder;
import client.openAIAPI.AudioTranscriber;
import client.openAIAPI.DallEImageGenerator;
import client.ServerErrorNotification;

/*
 * Class containing the logic necessary to 
 */
public class GenerateRecipesLogic {

    private AudioRecorder ar;
    private AudioTranscriber at;
    private String prompt;

    private boolean validPromptFlag;
    

    public GenerateRecipesLogic() {
        validPromptFlag = false;
    }

    public GenerateRecipeContent performGenerateButtonAction() {
        if (!validPromptFlag) {
            return new GenerateRecipeContent("Cannot Generate Recipe Without Valid Voice Prompt", null, null,null);
        } else {
            try {
                GenerateRecipeHandler grh = new GenerateRecipeHandler("Give me a recipe with " + this.prompt +
                        "in the format of title followed by ingredients, and then instructions for a recipe.");
                //Generate recipe based on prompt
                String recipeInfo = grh.makeRequest();
                String[] recipeInfoSplit = recipeInfo.split("\n");
                String recipeName = recipeInfoSplit[2];
                String recipeDetails = String.join("\n",
                        Arrays.copyOfRange(recipeInfoSplit, 3, recipeInfoSplit.length));

                //Generate image for recipe based on the name of the recipe
                DallEImageGenerator dig = new DallEImageGenerator(recipeName);
                byte[] imageArray = dig.generateImage();
                String mealType = null;

                //Set mealtype of recipe based on prompt
                if(this.prompt.contains("dinner") || this.prompt.contains("Dinner") ){
                    mealType = "Dinner";
                }
                else if(this.prompt.contains("breakfast") || this.prompt.contains("Breakfast")){
                    mealType = "Breakfast";
                }
                else if(this.prompt.contains("lunch") || this.prompt.contains("Lunch")){
                    mealType = "Lunch";
                }
                //Export the details of the recipe
                GenerateRecipeContent grc = new GenerateRecipeContent(recipeName, recipeDetails, imageArray, mealType);
                return grc;
            } catch (Exception ex) {
                ex.printStackTrace();
                ServerErrorNotification.alertNoConn();
                return new GenerateRecipeContent("Try again", null, null,null);
            }
            
        }
    }

    public void performStartButtonAction() {
        ar = new AudioRecorder();
        Thread thread = new Thread(ar);
        thread.start();
    }

    public String performStopButtonAction() {
        ar.finish();
        ar.cancel();

        at = new AudioTranscriber("src/client/audio/RecordAudio.wav");
        try {
            this.prompt = at.generateTranscription();
            //Check for valid meal type
            if (prompt.contains("dinner") || prompt.contains("Dinner") || prompt.contains("breakfast") ||
                    prompt.contains("Breakfast") || prompt.contains("lunch") || prompt.contains("Lunch")) {
                this.validPromptFlag = true;
                return prompt;
            } else {
                String noMealType = "Please Specify A Meal Type In Your Voice Prompt";
                return noMealType;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            ServerErrorNotification.alertNoConn();
        }
        return null;
    }

    public boolean getValidPromptFlag() {
        return this.validPromptFlag;
    }
}
