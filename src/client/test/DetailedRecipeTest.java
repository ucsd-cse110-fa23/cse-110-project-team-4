package client.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import models.Recipe;
import server.RecipeRepository;

public class DetailedRecipeTest {

    HashMap<UUID, Recipe> data = new HashMap<>();
    HashMap<String, UUID> nameIndex = new HashMap<>();
    RecipeRepository recipeRepository = new RecipeRepository(data, nameIndex);
    Recipe recipe1;
    Recipe recipe2;

    @BeforeEach
    void givenSomeRecipiesInMultipleView() {
        recipe1 = new Recipe(
                "b1a00706-1883-4c88-b3f8-b59d76e329bf;Huli Huli Chicken;yummy chicken plate with rice and mac;2178330567185300");
        data.put(recipe1.uuid, recipe1);
        nameIndex.put(recipe1.name, recipe1.uuid);
        recipe2 = new Recipe(
                "898d61b3-5598-4dfc-b8a2-2eb962354e3a;Yum Yum Bowl;pork bowl with veggies, rice, and yum yum sauce;2178360244910800");
        data.put(recipe2.uuid, recipe2);
        nameIndex.put(recipe2.name, recipe2.uuid);
    }

    @Test
    void testGetAndSetInfo() {
        Recipe r = recipeRepository.getRecipe(UUID.fromString("b1a00706-1883-4c88-b3f8-b59d76e329bf"));
        assertEquals("Huli Huli Chicken",r.name);
        assertEquals("yummy chicken plate with rice and mac",r.details);
        assertEquals(2178330567185300l,r.createdAt);
    }

    @Test
    void testEdit(){
        Recipe r = recipeRepository.getRecipe(UUID.fromString("898d61b3-5598-4dfc-b8a2-2eb962354e3a"));
        r.name = "Yum Bowl"; // user edits the name
        recipeRepository.editRecipe(r);
        r = recipeRepository.getRecipe(UUID.fromString("898d61b3-5598-4dfc-b8a2-2eb962354e3a"));
        assertEquals("Yum Bowl",r.name);
    }

    @Test
    void testSaveAndDelete(){
        //Save Recipe
        recipeRepository.createRecipe(new Recipe("698d61b3-5598-4dfc-b8a2-2eb962354e3a;Save me;Delete me later;2178360244910805"));
        Recipe r = recipeRepository.getRecipe(UUID.fromString("698d61b3-5598-4dfc-b8a2-2eb962354e3a"));
        assertEquals("Save me", r.name);
        //Delete Recipe
        recipeRepository.deleteRecipe(UUID.fromString("698d61b3-5598-4dfc-b8a2-2eb962354e3a"));
        r = recipeRepository.getRecipe(UUID.fromString("698d61b3-5598-4dfc-b8a2-2eb962354e3a"));
        assertNull(r);
    }
}
