package server;

import org.json.JSONObject;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Recipe {
    public ObjectId id;
    public String name;
    public String mealType;
    public String details;
    public ObjectId userId;
    public long createdAt;
    public String image;

    // constructor from different parts
    public Recipe(String id, String name, String mealType, String details, String userId, String image, long createdAt){
        this.id = new ObjectId(id);
        this.name = name;
        this.mealType = mealType;
        this.details = details;
        this.userId = new ObjectId(userId);
        this.createdAt = createdAt;
        this.image = image;
    }

    // constructor from a createRecipeJSON
    public Recipe(JSONObject createRecipeJSON){
        this.id = new ObjectId();
        this.name = createRecipeJSON.getString("name");        
        this.mealType = createRecipeJSON.getString("mealType");
        this.details = createRecipeJSON.getString("details");
        this.userId = new ObjectId(createRecipeJSON.getString("userId"));
        this.createdAt = System.currentTimeMillis();
    }

    // constructor from bson document
    public Recipe(Document recipeDocument){
        this.id = recipeDocument.getObjectId("_id");
        this.name = recipeDocument.getString("name");
        this.mealType = recipeDocument.getString("mealType");
        this.details = recipeDocument.getString("details");
        this.userId = recipeDocument.getObjectId("userId");
        this.createdAt = recipeDocument.getLong("createdAt");
        this.image = recipeDocument.getString("image");
    }

    public JSONObject toJSON(){
        JSONObject recipeJSON = new JSONObject();
        recipeJSON.put("createdAt", this.createdAt);
        recipeJSON.put("details", this.details);
        recipeJSON.put("mealType", this.mealType);
        recipeJSON.put("name", this.name);
        recipeJSON.put("id", this.id.toString());
        recipeJSON.put("image", this.image);
        return recipeJSON;
    }

}
