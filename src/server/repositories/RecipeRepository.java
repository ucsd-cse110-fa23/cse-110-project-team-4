package server.repositories;

import server.Recipe;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class RecipeRepository {

    private static final String CONNECTION_URI = 
            "mongodb+srv://cse110-lab6:iLoveCSE110@cluster0.e0wpva4.mongodb.net/?retryWrites=true&w=majority";
    private static final MongoClient mongoClient = MongoClients.create(CONNECTION_URI);
    private static final MongoDatabase pantryPalDB = mongoClient.getDatabase("pantryPal");
    private static final MongoCollection<Document> recipeCollection = pantryPalDB.getCollection("recipe");


    public RecipeRepository() {

    }

    // public ArrayList<String> getRecipeList() {
    //     ArrayList<String> recipeList = new ArrayList<String>();

    //     for (Recipe recipe : data.values())
    //         recipeList.add(recipe.uuid + "," + recipe.name);

    //     Collections.sort(recipeList,
    //             (a, b) -> (Long.compare(this.getRecipe(a).createdAt, this.getRecipe(b).createdAt) * -1));

    //     return recipeList;
    // }

    public Recipe createRecipe(JSONObject createRecipeJSON) {
        ObjectId id = new ObjectId();
        String name = createRecipeJSON.getString("name");
        String details = createRecipeJSON.getString("details");
        Long createdAt = System.currentTimeMillis();

        Document recipeDoc = new Document("_id", id);
        recipeDoc.append("name", name)
                .append("details", details)
                .append("createdAt", createdAt);

        recipeCollection.insertOne(recipeDoc);
        return new Recipe(id.toString(), name, details, createdAt);
    }

    public Recipe getRecipe(String id) {
        Document recipeDocument = recipeCollection.find(new Document("_id", new ObjectId(id))).first();
        Recipe recipe = new Recipe(recipeDocument);
        return recipe;
    }


    public Recipe editRecipe(JSONObject editRecipeRequest) {
        ObjectId id = new ObjectId(editRecipeRequest.getString("id"));
        Bson filter = eq("_id", id);
        Bson updateName = set("name", editRecipeRequest.getString("name"));
        Bson updatedDetails = set("name", editRecipeRequest.getString("name"));
        recipeCollection.updateOne(filter, updateName);
        recipeCollection.updateOne(filter, updatedDetails);

        return new Recipe(recipeCollection.find(filter).first());
    }

    public Recipe deleteRecipe(String id) {
        Bson filter = eq("_id", new ObjectId(id));
        Recipe recipe = new Recipe(recipeCollection.find(filter).first());
        recipeCollection.deleteOne(filter);
        return recipe;
    }
}