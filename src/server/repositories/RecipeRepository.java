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

import java.util.ArrayList;

public class RecipeRepository {

    private static final String CONNECTION_URI = 
            "mongodb+srv://cse110-lab6:iLoveCSE110@cluster0.e0wpva4.mongodb.net/?retryWrites=true&w=majority";
    private MongoClient mongoClient;
    private MongoDatabase pantryPalDB;
    private MongoCollection<Document> recipeCollection;


    public RecipeRepository() {
        this.mongoClient = MongoClients.create(CONNECTION_URI);
        this.pantryPalDB = mongoClient.getDatabase("pantryPal");
        this.recipeCollection = pantryPalDB.getCollection("recipe");
    }

    public RecipeRepository(String test) {
        this.mongoClient = MongoClients.create(CONNECTION_URI);
        this.pantryPalDB = mongoClient.getDatabase("pantryPalTest");
        this.recipeCollection = pantryPalDB.getCollection("recipe");
    }
    public ArrayList<Recipe> getRecipeList(String userId) {
        Bson filter = eq("userId", new ObjectId(userId));

        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        Iterable<Document> recipeDocuments = recipeCollection.find(filter);
        for(Document recipeDocument:recipeDocuments){
            recipeList.add(new Recipe(recipeDocument));
        }

        return recipeList;
    }

    public Recipe createRecipe(JSONObject createRecipeJSON) {
        Recipe recipe = new Recipe(createRecipeJSON);
        System.out.println(recipe.toJSON().toString());
        Document recipeDoc = new Document("_id", recipe.id);
        recipeDoc.append("name", recipe.name)
                .append("mealType", recipe.mealType)
                .append("details", recipe.details)
                .append("userId", recipe.userId)
                .append("createdAt", recipe.createdAt);

        recipeCollection.insertOne(recipeDoc);
        return recipe;
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
        Bson updatedDetails = set("details", editRecipeRequest.getString("details"));
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