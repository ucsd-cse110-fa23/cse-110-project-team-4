package server;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class RecipeRepository {

    private final Map<String, Recipe> data;

    public RecipeRepository(Map<String, String> data) {
        this.data = data;
    }

    public JSONObject getRecipe(String uuid){
        Recipe recipe = data[uuid];

        JSONObject recipeJSON = new JSONObject();
        recipeJSON.put("uuid", recipe.uuid);
        recipeJSON.put("name", recipe.name);
        recipeJSON.put("details", recipe.details);
        return recipeJSON;
    }

    public JSONObject createRecipe(Recipe recipe){
        data[recipe.uuid] = recipe;
        Recipe recipe = data[uuid];
        
        JSONObject recipeJSON = new JSONObject();
        recipeJSON.put("uuid", recipe.uuid);
        recipeJSON.put("name", recipe.name);
        recipeJSON.put("details", recipe.details);
    }

    public JSONObject editRecipe(JSONObject recipeEdit){
        JSONObject recipeJSON = new JSONObject();
        Recipe recipe = data[recipeEdit.getString("uuid")];
        recipe.name = recipeEdit.getString("name");
        recipe.details = recipeEdit.getString("details");
        return recipeEdit;
    }

    public void deleteRecipe(String uuid){
        data.remove(uuid);
    }

}