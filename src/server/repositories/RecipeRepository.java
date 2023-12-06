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

    // test constructor
    public RecipeRepository(String test) {
        this.mongoClient = MongoClients.create(CONNECTION_URI);
        this.pantryPalDB = mongoClient.getDatabase("pantryPalTest");
        this.recipeCollection = pantryPalDB.getCollection("recipe");
    }

    // gets recipe list by user
    public ArrayList<Recipe> getRecipeList(String userId) {
        Bson filter = eq("userId", new ObjectId(userId));

        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        Iterable<Document> recipeDocuments = recipeCollection.find(filter);
        for(Document recipeDocument:recipeDocuments){
            recipeList.add(new Recipe(recipeDocument));
        }

        return recipeList;
    }

    // creates recipe in database
    public Recipe createRecipe(JSONObject createRecipeJSON) {
        Recipe recipe = new Recipe(createRecipeJSON);
        System.out.println("yes");
        System.out.println(recipe.toJSON().toString());

        // creates the recipe document to be inserted
        Document recipeDoc = new Document("_id", recipe.id);
        recipeDoc.append("name", recipe.name)
                .append("mealType", recipe.mealType)
                .append("details", recipe.details)
                .append("userId", recipe.userId)
                .append("image", recipe.image)
                .append("createdAt", recipe.createdAt);

        recipeCollection.insertOne(recipeDoc);
        return recipe;
    }

    // gets recipe by id
    public Recipe getRecipe(String id) {
        Document recipeDocument = recipeCollection.find(new Document("_id", new ObjectId(id))).first();
        Recipe recipe = new Recipe(recipeDocument);
        return recipe;
    }

    // edits recipe
    public Recipe editRecipe(JSONObject editRecipeRequest) {
        ObjectId id = new ObjectId(editRecipeRequest.getString("id"));
        Bson filter = eq("_id", id);
        Bson updateName = set("name", editRecipeRequest.getString("name"));
        Bson updatedDetails = set("details", editRecipeRequest.getString("details"));
        recipeCollection.updateOne(filter, updateName);
        recipeCollection.updateOne(filter, updatedDetails);

        return new Recipe(recipeCollection.find(filter).first());
    }

    // deletes recipe
    public Recipe deleteRecipe(String id) {
        Bson filter = eq("_id", new ObjectId(id));
        Recipe recipe = new Recipe(recipeCollection.find(filter).first());
        recipeCollection.deleteOne(filter);
        return recipe;
    }

    // share recipe
    public String shareRecipe(String id) {
        Bson filter = eq("_id", new ObjectId(id));
        Document recipeDocument = recipeCollection.find(filter).first();
        if (recipeDocument == null) {
            return "No data found for recipe with id: " + id;
        }

        Recipe recipe = new Recipe(recipeDocument);
        StringBuilder htmlBuilder = new StringBuilder();

        // creates the html file
        htmlBuilder.append("<!DOCTYPE html>\n");
        htmlBuilder.append("<html>\n");
        htmlBuilder.append("<head>\n");
        htmlBuilder.append("<title>").append(recipe.name).append(" Recipe</title>\n");
        htmlBuilder.append("</head>\n");
        htmlBuilder.append("<body>\n");
        htmlBuilder.append("<h1>").append(recipe.name).append(" </h1>\n");
        htmlBuilder.append("<img src=").append("data:image/png;base64,"+recipe.image).append(">\n");
        htmlBuilder.append("<h2>").append(recipe.mealType).append(" </h2>\n");
        htmlBuilder.append("<p>").append(recipe.details.replaceAll("\n", "<br>")).append("</p>\n");
        htmlBuilder.append("</body>\n");
        htmlBuilder.append("</html>");

        // encode HTML content
        return htmlBuilder.toString();
    }
}