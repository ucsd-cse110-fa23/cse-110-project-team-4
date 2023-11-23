package client.test;

import java.lang.NullPointerException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import server.Recipe;
import server.repositories.RecipeRepository;

public class DetailedRecipeViewTests {

    private static final String CONNECTION_URI = 
            "mongodb+srv://cse110-lab6:iLoveCSE110@cluster0.e0wpva4.mongodb.net/?retryWrites=true&w=majority";
    private final MongoClient mongoClient = MongoClients.create(CONNECTION_URI);
    private final MongoDatabase pantryPalDB = mongoClient.getDatabase("pantryPal");
    private MongoCollection<Document> testRecipeCollection = pantryPalDB.getCollection("recipeTest");

    RecipeRepository recipeRepository = new RecipeRepository("recipeTest");

    Recipe recipe1;
    Recipe recipe2;

    @BeforeEach 
    void seedData() {
        recipe1 = new Recipe("655db6ee0eba1d4d1da76c4d", "Huli Huli Chicken", "lunch", 
            "yummy chicken plate with rice and mac", 1700640606057l);

        recipe2 = new Recipe("655ec290e597b112f51cdc2a", "Makai Bowl", "dinner", 
            "Poke bowl with salmon and ahi tuna", 1700709008320l);
        
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

    @AfterEach 
    void removeData() {
        testRecipeCollection.deleteMany(new Document());
    }

    @Test
    void testGetRecipe() {
        Recipe recipe = recipeRepository.getRecipe("655db6ee0eba1d4d1da76c4d");
        assertEquals("Huli Huli Chicken",recipe.name);
        assertEquals("yummy chicken plate with rice and mac",recipe.details);
        assertEquals(1700640606057l,recipe.createdAt);
    }

    @Test
    void testEdit(){
        Recipe recipe = recipeRepository.getRecipe("655db6ee0eba1d4d1da76c4d");
        recipe.name = "Huli Chicken"; // user edits the name
        recipeRepository.editRecipe(recipe.toJSON());
        recipe = recipeRepository.getRecipe("655db6ee0eba1d4d1da76c4d");
        assertEquals("Huli Chicken", recipe.name);
    }

    @Test
    void testSaveAndDelete(){
        //Save Recipe
        JSONObject createRecipeJson = new JSONObject();
        createRecipeJson.put("name", "Bobcat Ham");
        createRecipeJson.put("mealType", "breakfast");
        createRecipeJson.put("details", "Toasted ham egg and cheese sandwich");

        Recipe createRecipe = recipeRepository.createRecipe(createRecipeJson);
        Recipe getRecipe = recipeRepository.getRecipe(createRecipe.id.toString());
        assertEquals(createRecipe.toJSON().toString(), getRecipe.toJSON().toString());
        
        //Delete Recipe
        recipeRepository.deleteRecipe(createRecipe.id.toString());
        assertThrows(NullPointerException.class, () -> {
            recipeRepository.getRecipe(createRecipe.id.toString());
        });   
    } 
}
