package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import models.Recipe;

public class RecipeHandler implements HttpHandler {

    private final RecipeRepository recipeRepository;

    public RecipeHandler(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("POST")) {
                response = handlePost(httpExchange);
            } else if (method.equals("GET")) {
                response = handleGet(httpExchange);
            } else if (method.equals("PUT")) {
                response = handlePut(httpExchange);
            } else {
                throw new Exception("Not Valid Request Method");
            }
        } catch (Exception e) {
            System.out.println("An erroneous request");
            response = e.toString();
            e.printStackTrace();
        }

        // Sending back response to the client
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        outStream.write(response.getBytes());
        outStream.close();

    }

    private boolean isUUID(String s) {
        Pattern UUID_REGEX = Pattern
                .compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

        return UUID_REGEX.matcher(s).matches();
    }

    private String handleGet(HttpExchange httpExchange) throws IOException {
        /*
         * InputStream inStream = httpExchange.getRequestBody();
         * Scanner scanner = new Scanner(inStream);
         * String query = scanner.nextLine();
         * scanner.close();
         * 
         * Recipe r;
         * if (isUUID(query)) {
         * r = this.recipeRepository.getRecipe(UUID.fromString(query));
         * } else {
         * r = this.recipeRepository.getRecipe(query);
         * }
         * return r.toString();
         */

        String response = "Invalid GET request";
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        if (query != null) {
            String recipeName = query.substring(query.indexOf("=") + 1);

            String recipeDetails = this.recipeRepository.getRecipe(recipeName).toString(); // Retrieve data from hashmap
            if (recipeDetails != null) {
                response = recipeDetails;
                System.out.println("Queried for " + recipeName + " and found " + recipeDetails);
            } else {
                response = "No data found for " + recipeName;
            }
        }
        return response;

    }

    private String handlePost(HttpExchange httpExchange) throws IOException {
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String[] postData = scanner.nextLine().split(";");
        Recipe r = new Recipe(postData[0], postData[1]);
        this.recipeRepository.createRecipe(r);
        scanner.close();
        return r.toString();
    }

    private String handlePut(HttpExchange httpExchange) throws IOException {
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        Recipe r = new Recipe(scanner.nextLine());
        this.recipeRepository.editRecipe(r);
        scanner.close();
        return r.toString();
    }

}