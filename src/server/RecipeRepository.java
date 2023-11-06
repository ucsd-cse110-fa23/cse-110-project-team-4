package server;

import java.util.*; 
import models.Recipe;

public class RecipeRepository {

    private final Map<UUID, Recipe> data;
    private final Map<String, UUID> nameIndex;

    public RecipeRepository(Map<UUID, Recipe> data, Map<String, UUID> nameIndex) {
        this.data = data;
        this.nameIndex = nameIndex;
    }

    public ArrayList<String> getRecipeList(){
        ArrayList<String> recipeList = new ArrayList<String>();

        for (Recipe recipe : data.values())  
            recipeList.add(recipe.name); 

        return recipeList;
    }

    public void createRecipe(Recipe recipe){
        data.put(recipe.uuid, recipe);
        nameIndex.put(recipe.name, recipe.uuid);
    }

    public Recipe getRecipe(UUID uuid){
        return data.get(uuid);
    }

    public Recipe getRecipe(String name){
        return data.get(nameIndex.get(name));
    }

    public Recipe editRecipe(Recipe recipe){
        Recipe oldRecipe = data.get(recipe.uuid);
        if (!recipe.name.equals(oldRecipe.name)){
            nameIndex.remove(oldRecipe.name);
            nameIndex.put(recipe.name, recipe.uuid);
        }
        data.put(recipe.uuid, recipe);
        return recipe;
    }

    public Recipe deleteRecipe(UUID uuid){
        nameIndex.remove(data.get(uuid).name);
        return data.remove(uuid);
    }

    public Recipe deleteRecipe(String name){
        UUID uuid = nameIndex.remove(name);
        return data.remove(uuid);
    }
}