package client;

public class GenerateRecipeContent {
    private String recipeName;
    private String recipeDetails;
    private byte[] imageByteArray;
    private String mealType;

    GenerateRecipeContent(String recipeName, String recipeDetails, byte[] imageByteArray, String mealType) {
        this.recipeName = recipeName;
        this.recipeDetails = recipeDetails;
        this.imageByteArray = imageByteArray;
        this.mealType = mealType;
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

    public String getMealType(){
        return this.mealType;
    }
}
