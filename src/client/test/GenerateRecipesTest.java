package client.test;

import org.junit.jupiter.api.Test;

import client.*;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javafx.application.Platform;

public class GenerateRecipesTest {

    @BeforeAll
    static void initJfxRuntime() {
        Platform.startup(() -> {
        });
    }

    String sampleAudioFilePath = "src/client/audio/RecordAudio.wav";

    @Test
    void testGenerateRecipe() {
        AudioTranscriber at = new AudioTranscriber(sampleAudioFilePath);
        String sampleTranscription = at.generateFakeTranscription();
        GenerateRecipeHandler grh = new GenerateRecipeHandler(sampleTranscription);
        String sampleRecipe = grh.makeFakeRequest();

        assertNotNull(sampleRecipe);
        assertEquals("Here is a recipe for 'I want a dinner recipe for peas, carrots, and rice': Fried Rice",
                sampleRecipe);
    }

    @Test
    void test2() {
        JFXPanel jfxpanel = new JFXPanel();
        GenerateRecipesBody grb = new GenerateRecipesBody();

    }

}
