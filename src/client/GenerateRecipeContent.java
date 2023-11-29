package client;

public class GenerateRecipeContent {
    private String recipeName;
    private String recipeDetails;
    private byte[] imageByteArray;

    GenerateRecipeContent(String recipeName, String recipeDetails, byte[] imageByteArray) {
        this.recipeName = recipeName;
        this.recipeDetails = recipeDetails;
        this.imageByteArray = imageByteArray;
    }

    public String getRecipeName() {
        return this.recipeName;
    }
    public String getRecipeDetails() {
        return this.recipeDetails;
    }
    public byte[] getImageByteArray() {
        return this.imageByteArray;
    }
}
