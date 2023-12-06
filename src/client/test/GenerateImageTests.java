package client.test;

import org.junit.jupiter.api.Test;

import client.*;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GenerateImageTests {

    @Test
    void testGenerateMockImage() {
        String prompt = "Carrots and Peas Fried Rice";
        ImageGenerator dig = new DallEImageGeneratorMock(prompt);

        try {
            byte[] imageArray = dig.generateImage();
            assertNotNull(imageArray);
            byte[] expected = { (byte) 0xe0, 0x4f, (byte) 0xd0,
                0x20, (byte) 0xea, 0x3a, 0x69, 0x10 };
            assertArrayEquals(expected, imageArray);

            String encodedImage = Base64.getEncoder().encodeToString(imageArray);
            assertEquals("4E/QIOo6aRA=", encodedImage);
            assertArrayEquals(expected, Base64.getDecoder().decode(encodedImage));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
