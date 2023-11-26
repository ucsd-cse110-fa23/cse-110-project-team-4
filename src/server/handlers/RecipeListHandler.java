package server.handlers;

import com.sun.net.httpserver.*;

import server.Recipe;
import server.repositories.RecipeRepository;

import java.io.*;
import java.net.URI;
import java.util.*;

import org.json.JSONArray;

public class RecipeListHandler implements HttpHandler {

    private final RecipeRepository recipeRepository;

    public RecipeListHandler(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("GET")) {
              response = handleGet(httpExchange);
            } else {
                throw new Exception("Not Valid Request Method");
            }
          } catch (Exception e) {
              System.out.println("An erroneous request");
              response = e.toString();
              e.printStackTrace();
        }

        //Sending back response to the client
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        outStream.write(response.getBytes());
        outStream.close();
       
    }

    private String handleGet(HttpExchange httpExchange) throws IOException {
        String response = "Invalid GET request";
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();


        if (query != null) {
            String userId = query.substring(query.indexOf("=") + 1);
            
            ArrayList<Recipe> recipeList = this.recipeRepository.getRecipeList(userId); // Retrieve data from hashmap
            JSONArray recipeListJSON = new JSONArray();
            for(Recipe recipe:recipeList){
                recipeListJSON.put(recipe.toJSON());
            }
            response = recipeListJSON.toString();
        }

        return response;
    }     

}