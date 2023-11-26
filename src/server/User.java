package server;

import org.json.JSONObject;
import org.bson.Document;
import org.bson.types.ObjectId;

public class User {
    public ObjectId id;
    public String username;
    public String password;

    // constructor from different parts
    public User(String id, String username, String password){
        this.id = new ObjectId(id);
        this.username = username;
        this.password = password;
    }

    // constructor from a createRecipeJSON
    public User(JSONObject createUserJSON){
        this.id = new ObjectId();
        this.username = createUserJSON.getString("username");        
        this.password = createUserJSON.getString("password");
    }

    // constructor from bson document
    public User(Document userDocument){
        this.id = userDocument.getObjectId("_id");
        this.username = userDocument.getString("username");
        this.password = userDocument.getString("password");
    }

    public JSONObject toJSON(){
        JSONObject recipeJSON = new JSONObject();
        recipeJSON.put("password", this.password);
        recipeJSON.put("username", this.username);
        recipeJSON.put("id", this.id.toString());
        return recipeJSON;
    }

}
