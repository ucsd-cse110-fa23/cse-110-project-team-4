package client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONObject;

public class GenerateRecipeHandler {

    private static final String API_ENDPOINT = "https://api.openai.com/v1/completions";
    private static final String API_KEY = "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";
    private static final String MODEL = "text-davinci-003";

    JSONObject requestBody;
    HttpClient client;

    String prompt;
    String generatedText;

    int maxTokens;

    public GenerateRecipeHandler(String prompt) {
        // Set request parameters
        this.prompt = prompt;
        this.maxTokens = 500;

        // Create a request body which you will pass into request object
        this.requestBody = new JSONObject();
        requestBody.put("model", MODEL);
        requestBody.put("prompt", prompt);
        requestBody.put("max_tokens", maxTokens);
        requestBody.put("temperature", 1.0);

        this.client = HttpClient.newHttpClient();

    }

    public String makeRequest() {
        try {
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(new URI(API_ENDPOINT))
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
            JSONArray choices = responseJson.getJSONArray("choices");
            String generatedText = choices.getJSONObject(0).getString("text");
            this.generatedText = generatedText;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.generatedText;
    }

    public String makeFakeRequest() {
        this.generatedText = String.format("Here is a recipe for '%s': Fried Rice", this.prompt);
        return this.generatedText;
    }

}
