package client.test;

import org.junit.jupiter.api.Test;

import client.*;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javafx.application.Platform;

public class GenerateRecipesTest {

    String sampleAudioFilePath = "src/client/audio/RecordAudio.wav";
    GenerateRecipesLogicMock grl = new GenerateRecipesLogicMock();

    @Test
    void testGenerateMockRecipe() {
        AudioTranscriberMock at = new AudioTranscriberMock(sampleAudioFilePath);

        try {
            String sampleTranscription = at.generateTranscription();

            GenerateRecipeHandlerMock grh = new GenerateRecipeHandlerMock(sampleTranscription);
            String sampleRecipe = grh.makeRequest();

            assertNotNull(sampleRecipe);
            assertEquals("Here is a recipe for 'I want a dinner recipe for peas, carrots, and rice': Fried Rice",
                    sampleRecipe);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void testGenerateButtonNotValidPrompt() {
        // Valid Prompt has not been given yet
        assertFalse(grl.getValidPromptFlag());
        assertNull(grl.performGenerateButtonAction());
    }

    @Test
    void testGenerateButtonValidPrompt() {
        // false generates a good mock voice prompt
        grl.performStopButtonAction(false);
        assertTrue(grl.getValidPromptFlag());
        assertNotNull(grl.performGenerateButtonAction());

        String[] recipeContent = {"", ""};
        recipeContent[0] = "Fried Rice";
        recipeContent[1] = "Peas, carrots, and rice";
        assertArrayEquals(recipeContent, grl.performGenerateButtonAction());
    }

    @Test
    void testStopButtonNotValidPrompt() {
        String noMealType = "Please Specify A Meal Type In Your Voice Prompt";
        // true generates a mock bad voice prompt
        String prompt = grl.performStopButtonAction(true);
        assertEquals(noMealType, prompt);
    }

    @Test
    void testStopButtonValidPrompt() {
        String validPrompt = "I want a dinner recipe for peas, carrots, and rice";
        assertEquals(validPrompt, grl.performStopButtonAction(false));
    }
}
