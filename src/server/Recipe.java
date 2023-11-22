package server;

import org.json.JSONObject;
import org.bson.Document;

public class Recipe {
    public String id;
    public String name;
    public String details;
    public long createdAt;

    public Recipe(String id, String name, String details, long createdAt){
        this.id = id;
        this.name = name;
        this.details = details;
        this.createdAt = createdAt;
    }

    public Recipe(JSONObject recipeJSON){
        this.id = recipeJSON.getString("_id");
        this.name = recipeJSON.getString("name");
        this.details = recipeJSON.getString("details");
        this.createdAt = recipeJSON.getLong("createdAt");
    }

    public Recipe(Document recipeDocument){
        this.id = recipeDocument.get("_id").toString();
        this.name = recipeDocument.getString("name");
        this.details = recipeDocument.getString("details");
        this.createdAt = recipeDocument.getLong("createdAt");
    }

    public JSONObject toJSON(){
        JSONObject recipeJSON = new JSONObject();
        recipeJSON.put("id", this.id);
        recipeJSON.put("name", this.name);
        recipeJSON.put("details", this.details);
        recipeJSON.put("createdAt", this.createdAt);
        return recipeJSON;
    }

}
