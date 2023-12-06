package client.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import client.RecipeShareLogic;

public class RecipeShareLinkTest {
    String recipe1UUID = "655db6ee0eba1d4d1da76c4d";
    String recipe2UUID = "655ec290e597b112f51cdc2a";

    @Test
    void testLink(){
        String link = RecipeShareLogic.createShareLinkString(recipe1UUID);

        assertEquals("localhost:8100/recipe/share?=655db6ee0eba1d4d1da76c4d", link);
    }

}
