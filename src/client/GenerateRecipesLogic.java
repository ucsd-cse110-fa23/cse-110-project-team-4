package client;

import java.util.Arrays;


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
            return null;
        } else {
            try {
                GenerateRecipeHandler grh = new GenerateRecipeHandler("Give me a recipe with no semicolons or commas" + this.prompt +
                        "in the format of title followed by ingredients, and then instructions for a recipe.");
                String recipeInfo = grh.makeRequest();
                String[] recipeInfoSplit = recipeInfo.split("\n");
                String recipeName = recipeInfoSplit[2];
                String recipeDetails = String.join("\n",
                        Arrays.copyOfRange(recipeInfoSplit, 3, recipeInfoSplit.length));

                DallEImageGenerator dig = new DallEImageGenerator(recipeName);
                byte[] imageArray = dig.generateImage();

                GenerateRecipeContent grc = new GenerateRecipeContent(recipeName, recipeDetails, imageArray);
                return grc;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
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
        }
        return null;
    }

    public boolean getValidPromptFlag() {
        return this.validPromptFlag;
    }
}
