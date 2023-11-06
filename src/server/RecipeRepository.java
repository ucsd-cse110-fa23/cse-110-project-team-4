package server;

import java.util.*; 
import models.Recipe;

public class RecipeRepository {

    private final Map<String, Recipe> data;

    public RecipeRepository(Map<String, Recipe> data) {
        this.data = data;
    }

    public ArrayList<String> getRecipeList(){
        ArrayList<String> recipeList = new ArrayList<String>();

        for (Recipe recipe : data.values())  
            recipeList.add(recipe.name); 

        return recipeList;
    }

    public void createRecipe(Recipe recipe){
        data.put(recipe.uuid, recipe);
    }
}