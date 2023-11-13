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

    String sampleAudioFilePath = "src/client/audio/RecordAudio.wav";

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
    void testGenerateButtonLogic() {

    }
}
