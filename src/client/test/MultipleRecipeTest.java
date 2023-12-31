package client.test;

import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import server.Recipe;
import server.User;
import server.repositories.RecipeRepository;

public class MultipleRecipeTest {

    private static final String CONNECTION_URI = 
            "mongodb+srv://cse110-lab6:iLoveCSE110@cluster0.e0wpva4.mongodb.net/?retryWrites=true&w=majority";
    private final MongoClient mongoClient = MongoClients.create(CONNECTION_URI);
    private final MongoDatabase pantryPalDB = mongoClient.getDatabase("pantryPalTest");
    private MongoCollection<Document> testRecipeCollection = pantryPalDB.getCollection("recipe");
    private MongoCollection<Document> testUserCollection = pantryPalDB.getCollection("user");

    RecipeRepository recipeRepository = new RecipeRepository("Test");
    Recipe recipe1, recipe2, recipe3;
    User user1, user2;

    void insertRecipe(Recipe recipe) {
        Document recipeDoc = new Document("_id", recipe.id);
        recipeDoc.append("name", recipe.name)
                .append("mealType", recipe.mealType)
                .append("details", recipe.details)
                .append("userId", recipe.userId)
                .append("createdAt", recipe.createdAt);

        testRecipeCollection.insertOne(recipeDoc);
    }

    void insertUser(User user) {
        Document userDoc = new Document("_id", user.id);
        userDoc.append("name", user.username)
                .append("password", user.password);

        testUserCollection.insertOne(userDoc);
    }

    @BeforeEach 
    void seedData() {
        testRecipeCollection.deleteMany(new Document());
        testUserCollection.deleteMany(new Document());

        recipe1 = new Recipe("655db6ee0eba1d4d1da76c4d", "Huli Huli Chicken", "lunch", 
            "yummy chicken plate with rice and mac", 
            "65614b0c44879f466638921b",
            null, 1700640606057l);

        recipe2 = new Recipe("655ec290e597b112f51cdc2a", "Makai Bowl", "dinner", 
            "Poke bowl with salmon and ahi tuna", 
            "65614b0c44879f466638921b",
            null, 1700709008320l);

        recipe3 = new Recipe("655f2290e597b112f51cdc2a", "Bobcat Ham", "breakfast", 
            "Toasted ham egg and cheese sandwich", 
            "65614b0c44879f466638921b",
            null, 1700709008320l);

        insertRecipe(recipe1);
        insertRecipe(recipe2);
        insertRecipe(recipe3);

        user1 = new User("65614b0c44879f466638921b", "maxwn04", "passw0rd!");
        user2 = new User("65614b0c44879f477738921b", "arvinz", "pa55w0rd#");
        insertUser(user1);
        insertUser(user2);
    }

    @Test
    void testGetList() {
        ArrayList<Recipe> recipeList = recipeRepository.getRecipeList(user1.id.toString());
        assertEquals(recipe1.toJSON().toString(), recipeList.get(0).toJSON().toString());
        assertEquals(recipe2.toJSON().toString(), recipeList.get(1).toJSON().toString());
        assertEquals(recipe3.toJSON().toString(), recipeList.get(2).toJSON().toString());

        recipeList = recipeRepository.getRecipeList(user2.id.toString());
        assertEquals((Integer) 0, recipeList.size());
    }

    // @Test
    // void testEmptyList() {
    //     List<String> recipeNames = Arrays.asList(new String[] {});
    //     assertEquals(recipeNames, recipeRepository.getRecipeList());
    // }

}
