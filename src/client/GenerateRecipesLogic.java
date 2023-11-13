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
    private GenerateRecipesViewController grvc;

    private GenerateRecipesBody grb;

    public GenerateRecipesLogic(GenerateRecipesViewController grvc, GenerateRecipesBody grb) {
        validPromptFlag = false;
        this.grvc = grvc;
        this.grb = grb;
    }

    public void performGenerateButtonAction() {
        if (!validPromptFlag) {
            String cannotGenerateNow = "Cannot Generate Recipe Without Valid Voice Prompt";
            this.grb.setTranscription(cannotGenerateNow);
        } else {
            try {
                GenerateRecipeHandler grh = new GenerateRecipeHandler("Give me a recipe with" + this.prompt +
                        "in the format of title followed by ingredients, and then instructions for a recipe.");

                String recipeInfo = grh.makeRequest();
                String[] recipeInfoSplit = recipeInfo.split("\n");
                String recipeName = recipeInfoSplit[2];
                String recipeDetails = String.join("\n",
                        Arrays.copyOfRange(recipeInfoSplit, 3, recipeInfoSplit.length));

                this.grvc.exportRecipeToDetailed(recipeName, recipeDetails);
                this.grvc.transitionToDetailed();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }

    public void performStartButtonAction() {
        ar = new AudioRecorder();
        Thread thread = new Thread(ar);
        thread.start();
    }

    public void performStopButtonAction() {
        ar.finish();
        ar.cancel();

        at = new AudioTranscriber("src/client/audio/RecordAudio.wav");
        try {
            this.prompt = at.generateTranscription();
            if (prompt.contains("dinner") || prompt.contains("Dinner") || prompt.contains("breakfast") ||
                    prompt.contains("Breakfast") || prompt.contains("lunch") || prompt.contains("Lunch")) {
                this.validPromptFlag = true;
                this.grb.setTranscription(prompt);
            } else {
                String noMealType = "Please Specify A Meal Type In Your Voice Prompt";
                grb.setTranscription(noMealType);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean getValidPromptFlag() {
        return this.validPromptFlag;
    }
}
