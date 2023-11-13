package client.test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

import models.Recipe;
import server.RecipeRepository;

public class MultipleRecipeTest {

  HashMap<UUID, Recipe> data = new HashMap<>();
  HashMap<String, UUID> nameIndex = new HashMap<>();
  RecipeRepository recipeRepository = new RecipeRepository(data, nameIndex);
  Recipe recipe1;
  Recipe recipe2;
  Recipe recipe3;

  @Test
  void testListSorted() {

    recipe1 = new Recipe(
        "b1a00706-1883-4c88-b3f8-b59d76e329bf;Huli Huli Chicken;yummy chicken plate with rice and mac;2178330567185300");
    data.put(recipe1.uuid, recipe1);
    nameIndex.put(recipe1.name, recipe1.uuid);

    recipe2 = new Recipe(
        "898d61b3-5598-4dfc-b8a2-2eb962354e3a;Yum Yum Bowl;pork bowl with veggies, rice, and yum yum sauce;2178360244919800");
    data.put(recipe2.uuid, recipe2);
    nameIndex.put(recipe2.name, recipe2.uuid);

    recipe3 = new Recipe(
        "8c215db3-2513-4d1c-3c2b-24d162354e3a;Oyakodon Chicken;stir fried mirin chicken with egg omelette served over rice;2178360244910800");
    data.put(recipe3.uuid, recipe3);
    nameIndex.put(recipe3.name, recipe3.uuid);

    List<String> recipeNames = Arrays.asList(new String[] { "898d61b3-5598-4dfc-b8a2-2eb962354e3a,Yum Yum Bowl",
        "8c215db3-2513-4d1c-3c2b-24d162354e3a,Oyakodon Chicken",
        "b1a00706-1883-4c88-b3f8-b59d76e329bf,Huli Huli Chicken" });
    assertEquals(recipeNames, recipeRepository.getRecipeList());
  }

  @Test
  void testEmptyList() {
    List<String> recipeNames = Arrays.asList(new String[] {});
    assertEquals(recipeNames, recipeRepository.getRecipeList());
  }

}
