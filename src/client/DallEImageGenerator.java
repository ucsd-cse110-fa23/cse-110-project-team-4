package client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import org.json.JSONObject;

public class DallEImageGenerator {
    private static final String API_ENDPOINT = "https://api.openai.com/v1/images/generations";
    private static final String API_KEY = "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";
    private static final String MODEL = "dall-e-2";

    private String prompt;

    public DallEImageGenerator(String prompt) {
        this.prompt = prompt;
    }

    public byte[] generateImage()
            throws IOException, InterruptedException, URISyntaxException {
        // Set request parameters
        int n = 1;

        // String imagePath = "src/client/images/" + prompt + ".jpg";
        // Files.deleteIfExists(Paths.get(imagePath));

        // Create a request body which you will pass into request object
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", this.prompt);
        requestBody.put("n", n);
        requestBody.put("size", "256x256");

        // Create the HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create the request object
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(API_ENDPOINT))
                .header("Content-Type", "application/json")
                .header("Authorization", String.format("Bearer %s", API_KEY))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                .build();

        // Send the request and receive the response
        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString());

        // Process the response
        String responseBody = response.body();

        JSONObject responseJson = new JSONObject(responseBody);
        String generatedImageURL = responseJson.getJSONArray("data").getJSONObject(0).getString("url");

        System.out.println("DALL-E Response:");
        System.out.println(generatedImageURL);

        BufferedImage bi = ImageIO.read(new URI(generatedImageURL).toURL());
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", b);

        byte[] imageByteArray = b.toByteArray();
        //String imageString = new String(imageByteArray);
        return imageByteArray;

    }
}
