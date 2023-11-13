package client;

public class GenerateRecipeHandlerMock implements ChatGPTHandler{

    String generatedText;
    String prompt;

    public GenerateRecipeHandlerMock(String prompt) {
        this.prompt = prompt;
    }

    public String makeRequest() {
        this.generatedText = String.format("Here is a recipe for '%s': Fried Rice", this.prompt);
        return this.generatedText;
    }
}
