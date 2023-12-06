package client.test;

import org.bson.Document;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import server.Recipe;
import server.User;
import server.repositories.RecipeRepository;

public class SortRecipeTest {
  private static final String CONNECTION_URI = "mongodb+srv://cse110-lab6:iLoveCSE110@cluster0.e0wpva4.mongodb.net/?retryWrites=true&w=majority";
  private final MongoClient mongoClient = MongoClients.create(CONNECTION_URI);
  private final MongoDatabase pantryPalDB = mongoClient.getDatabase("pantryPalTest");
  private MongoCollection<Document> testRecipeCollection = pantryPalDB.getCollection("recipe");
  private MongoCollection<Document> testUserCollection = pantryPalDB.getCollection("user");

  RecipeRepository recipeRepository = new RecipeRepository("Test");
  Recipe recipe1, recipe2, recipe3;
  User user1, user2;
  List<JSONObject> recipeArrayList;

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
    recipeArrayList = new ArrayList<JSONObject>();

    recipe1 = new Recipe("655db6ee0eba1d4d1da76c4d", "Huli Huli Chicken", "lunch",
        "yummy chicken plate with rice and mac",
        "65614b0c44879f466638921b",
        null, 1700640606057l);

    recipe2 = new Recipe("655ec290e597b112f51cdc2a", "Makai Bowl", "dinner",
        "Poke bowl with salmon and ahi tuna",
        "65614b0c44879f466638921b",
        null, 1700809008320l);

    recipe3 = new Recipe("655f2290e597b112f51cdc2a", "Bobcat Ham", "breakfast",
        "Toasted ham egg and cheese sandwich",
        "65614b0c44879f466638921b",
        null, 1700709008320l);

    insertRecipe(recipe1);
    insertRecipe(recipe2);
    insertRecipe(recipe3);

    user1 = new User("65614b0c44879f466638921b", "maxwn04", "passw0rd!");
    insertUser(user1);
  }

  public void handleSortByName(Boolean reverseOrder) {
    Collections.sort(recipeArrayList, new Comparator<JSONObject>() {
      @Override
      public int compare(JSONObject a, JSONObject b) {
        String valA;
        String valB;

        valA = a.getString("name");
        valB = b.getString("name");

        int comp = valA.compareTo(valB);

        return comp;
      }
    });

    if (reverseOrder) {
      Collections.reverse(recipeArrayList);
    }
  }

  public void handleSortByTime(Boolean reverseOrder) {
    Collections.sort(recipeArrayList, new Comparator<JSONObject>() {
      @Override
      public int compare(JSONObject a, JSONObject b) {
        Long valA;
        Long valB;

        valA = a.getLong("createdAt");
        valB = b.getLong("createdAt");

        int comp = Long.compare(valA, valB);

        return comp;
      }
    });

    if (reverseOrder) {
      Collections.reverse(recipeArrayList);
    }
  }

  @Test
  void testSortAlphabetically() {
    ArrayList<Recipe> recipeList = recipeRepository.getRecipeList(user1.id.toString());
    recipeArrayList.clear();
    for (int i = 0; i < recipeList.size(); i++) {
      recipeArrayList.add(recipeList.get(i).toJSON());
    }

    handleSortByName(false);
    assertEquals(recipe3.toJSON().toString(), recipeArrayList.get(0).toString());
    assertEquals(recipe1.toJSON().toString(), recipeArrayList.get(1).toString());
    assertEquals(recipe2.toJSON().toString(), recipeArrayList.get(2).toString());

    handleSortByName(true);
    assertEquals(recipe2.toJSON().toString(), recipeArrayList.get(0).toString());
    assertEquals(recipe1.toJSON().toString(), recipeArrayList.get(1).toString());
    assertEquals(recipe3.toJSON().toString(), recipeArrayList.get(2).toString());
  }

  @Test
  void testSortChronologically() {
    ArrayList<Recipe> recipeList = recipeRepository.getRecipeList(user1.id.toString());
    recipeArrayList.clear();
    for (int i = 0; i < recipeList.size(); i++) {
      recipeArrayList.add(recipeList.get(i).toJSON());
    }

    handleSortByTime(false);
    assertEquals(recipe1.toJSON().toString(), recipeArrayList.get(0).toString());
    assertEquals(recipe3.toJSON().toString(), recipeArrayList.get(1).toString());
    assertEquals(recipe2.toJSON().toString(), recipeArrayList.get(2).toString());

    handleSortByTime(true);
    assertEquals(recipe2.toJSON().toString(), recipeArrayList.get(0).toString());
    assertEquals(recipe3.toJSON().toString(), recipeArrayList.get(1).toString());
    assertEquals(recipe1.toJSON().toString(), recipeArrayList.get(2).toString());
  }
}
