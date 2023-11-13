package client;

public class GenerateRecipesLogicMock {
    
    private AudioRecorder ar;
    private AudioTranscriberMock at;
    private String prompt;

    private boolean validPromptFlag;
    

    public GenerateRecipesLogicMock() {
        validPromptFlag = false;
    }

    public String[] performGenerateButtonAction() {
        if (!validPromptFlag) {
            return null;
        } else {
            try {
                String[] toReturn = {"", ""};
                toReturn[0] = "Fried Rice";
                toReturn[1] = "Peas, carrots, and rice";
                return toReturn;
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

    public String performStopButtonAction(boolean badPrompt) {
        // ar.finish();
        // ar.cancel();

        at = new AudioTranscriberMock("src/client/audio/RecordAudio.wav");
        try {
            if(badPrompt) {
                this.prompt = at.generateBadTranscription();
            }
            else {
                this.prompt = at.generateTranscription();
            }
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
