package server;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;  
import models.Recipe;

public class RecipeRepository {

    private final Map<String, Recipe> data;

    public RecipeRepository(Map<String, Recipe> data) {
        this.data = data;
    }

    public JSONObject getRecipe(String uuid) throws Exception{
        Recipe recipe = data.get(uuid);

        JSONObject recipeJSON = new JSONObject();
        recipeJSON.put("uuid", recipe.uuid);
        recipeJSON.put("name", recipe.name);
        recipeJSON.put("details", recipe.details);
        return recipeJSON;
    }

    public JSONObject createRecipe(Recipe recipe) throws Exception {
        data.put(recipe.uuid, recipe);
        Recipe recipeResponse = data.get(recipe.uuid);
        
        JSONObject recipeJSON = new JSONObject();
        recipeJSON.put("uuid", recipeResponse.uuid);
        recipeJSON.put("name", recipeResponse.name);
        recipeJSON.put("details", recipeResponse.details);
        return recipeJSON;
    }

    public JSONObject editRecipe(JSONObject recipeEdit) throws Exception{
        JSONObject recipeJSON = new JSONObject();
        Recipe recipe = data.get(recipeEdit.getString("uuid"));
        recipe.name = recipeEdit.getString("name");
        recipe.details = recipeEdit.getString("details");
        return recipeEdit;
    }

    public void deleteRecipe(String uuid){
        data.remove(uuid);
    }

}