package client.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

import server.Recipe;
import server.repositories.RecipeRepository;

import java.util.*;

public class ServerTest {

    
    HashMap<UUID, Recipe> data = new HashMap<>();
    HashMap<String, UUID> nameIndex = new HashMap<>();
    RecipeRepository recipeRepository = new RecipeRepository(data, nameIndex);
    Recipe recipe1;
    Recipe recipe2;

    @BeforeEach 
    void seedData() {
        recipe1 = new Recipe
                ("b1a00706-1883-4c88-b3f8-b59d76e329bf;Huli Huli Chicken;yummy chicken plate with rice and mac;2178330567185300");
        data.put(recipe1.uuid, recipe1);
        nameIndex.put(recipe1.name, recipe1.uuid);
        recipe2 = new Recipe
                ("898d61b3-5598-4dfc-b8a2-2eb962354e3a;Yum Yum Bowl;pork bowl with veggies, rice, and yum yum sauce;2178360244910800");
        data.put(recipe2.uuid, recipe2);
        nameIndex.put(recipe2.name, recipe2.uuid);
    }

    @Test
    void testGetList() {
        List<String> recipeNames = Arrays.asList(new String[] {"898d61b3-5598-4dfc-b8a2-2eb962354e3a,Yum Yum Bowl","b1a00706-1883-4c88-b3f8-b59d76e329bf,Huli Huli Chicken"});
        assertEquals(recipeNames, recipeRepository.getRecipeList());
    }

    @Test
    void testGetByUUID() {
        assertEquals(recipe1, recipeRepository.getRecipe(UUID.fromString("b1a00706-1883-4c88-b3f8-b59d76e329bf")));
        assertEquals(recipe2, recipeRepository.getRecipe(UUID.fromString("898d61b3-5598-4dfc-b8a2-2eb962354e3a")));
    }

    @Test
    void testGetByName() {
        assertEquals(recipe1, recipeRepository.getRecipe(recipe1.uuid+","+"Huli Huli Chicken"));
        assertEquals(recipe2, recipeRepository.getRecipe(recipe2.uuid+","+"Yum Yum Bowl"));
    }

    @Test
    void testCreate() {
        Recipe newRecipe = new Recipe("Makai Bowl", "poke bowl with stuff");
        recipeRepository.createRecipe(newRecipe);
        assertEquals(newRecipe, recipeRepository.getRecipe(newRecipe.uuid+","+"Makai Bowl"));
    }
}
