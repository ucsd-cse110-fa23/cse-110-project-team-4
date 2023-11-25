package client.test;

import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import server.Recipe;
import server.repositories.RecipeRepository;

public class ServerTest {

    private static final String CONNECTION_URI = 
            "mongodb+srv://cse110-lab6:iLoveCSE110@cluster0.e0wpva4.mongodb.net/?retryWrites=true&w=majority";
    private final MongoClient mongoClient = MongoClients.create(CONNECTION_URI);
    private final MongoDatabase pantryPalDB = mongoClient.getDatabase("pantryPalTest");
    private MongoCollection<Document> testRecipeCollection = pantryPalDB.getCollection("recipe");

    RecipeRepository recipeRepository = new RecipeRepository("recipeTest");

    Recipe recipe1;
    Recipe recipe2;

    @BeforeEach 
    void seedData() {
        testRecipeCollection.deleteMany(new Document());

        recipe1 = new Recipe("655db6ee0eba1d4d1da76c4d", "Huli Huli Chicken", "lunch", 
            "yummy chicken plate with rice and mac", 
            "655db6ee0eba1d4d1da76c4d",
            1700640606057l);

        recipe2 = new Recipe("655ec290e597b112f51cdc2a", "Makai Bowl", "dinner", 
            "Poke bowl with salmon and ahi tuna", 
            "655db6ee0eba1d4d1da76c4d",
            1700709008320l);
        
        Document recipeDoc = new Document("_id", recipe1.id);
        recipeDoc.append("name", recipe1.name)
                .append("mealType", recipe1.mealType)
                .append("details", recipe1.details)
                .append("createdAt", recipe1.createdAt);

        testRecipeCollection.insertOne(recipeDoc);

        recipeDoc = new Document("_id", recipe2.id);
        recipeDoc.append("name", recipe2.name)
                .append("mealType", recipe2.mealType)
                .append("details", recipe2.details)
                .append("createdAt", recipe2.createdAt);

        testRecipeCollection.insertOne(recipeDoc);
    }

    @Test
    void testGetByUUID() {
        Recipe recipeResponse = recipeRepository.getRecipe("655db6ee0eba1d4d1da76c4d");
        assertEquals(recipe1.toJSON().toString(), recipeResponse.toJSON().toString());

        recipeResponse = recipeRepository.getRecipe("655ec290e597b112f51cdc2a");
        assertEquals(recipe2.toJSON().toString(), recipeResponse.toJSON().toString());
    }

    @Test
    void testCreate() {
        JSONObject createRecipeJson = new JSONObject();
        createRecipeJson.put("name", "Bobcat Ham");
        createRecipeJson.put("mealType", "breakfast");
        createRecipeJson.put("details", "Toasted ham egg and cheese sandwich");
        createRecipeJson.put("userId", "65614b0c44879f466638921b");

        Recipe newRecipe = recipeRepository.createRecipe(createRecipeJson);
        Recipe getNewRecipe = recipeRepository.getRecipe(newRecipe.id.toString());
        assertEquals(newRecipe.toJSON().toString(), getNewRecipe.toJSON().toString());
    }
}
