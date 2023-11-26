package server.handlers;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.URI;
import java.util.*;

import org.json.JSONObject;

import server.Recipe;
import server.repositories.RecipeRepository;

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
            } else if (method.equals("DELETE")) {
                response = handleDelete(httpExchange);
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

    // Takes in the httpExchange and gets the body as a JSON
    private JSONObject parseJSON(HttpExchange httpExchange) {
        InputStream inStream = httpExchange.getRequestBody();
        StringBuilder jsonBuff = new StringBuilder();
        String line = null;
        try {
            Scanner scanner = new Scanner(inStream);;
            while ((line = scanner.nextLine()) != null)
                jsonBuff.append(line);
            scanner.close();
        } catch (Exception e) { /*error*/ 
            e.printStackTrace();
        }

        //System.out.println("Request JSON string :" + jsonBuff.toString());
        //write the response here by getting JSON from jasonBuff.toString()


        JSONObject jsonObject = new JSONObject(jsonBuff.toString());
        return jsonObject;
    }

    private String handleGet(HttpExchange httpExchange) throws IOException {

        String response = "Invalid GET request";
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        
        if (query != null) {
            String recipeID = query.substring(query.indexOf("=") + 1);
            
            Recipe recipe = this.recipeRepository.getRecipe(recipeID); // Retrieve data from hashmap
            if (recipe != null) {
                response = recipe.toJSON().toString();
                System.out.println("Queried for " + recipe.name + " and found " + recipe.details);
                return response;
            } else {
                response = "No data found for " + recipeID;
            }
        }
        return response;
    }     

    private String handlePost(HttpExchange httpExchange) throws IOException {
        JSONObject createRecipeRequest = parseJSON(httpExchange);
        Recipe recipe = this.recipeRepository.createRecipe(createRecipeRequest);
        return recipe.toJSON().toString();
    }   
    
    private String handlePut(HttpExchange httpExchange) throws IOException {
        JSONObject editRecipeRequest = parseJSON(httpExchange);
        Recipe recipe = this.recipeRepository.editRecipe(editRecipeRequest);
        return recipe.toJSON().toString();
    }   

    private String handleDelete(HttpExchange httpExchange) {
        String response = "Invalid DELETE request";
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        if (query != null) {
            String recipeID = query.substring(query.indexOf("=") + 1);
            Recipe recipe = this.recipeRepository.getRecipe(recipeID);
            if(recipe != null) {
                this.recipeRepository.deleteRecipe(recipeID);
                response = "Deleted entry {" + recipe.name + ", " + recipe.toJSON().toString() + "}";
                System.out.println(response);
            }
            else {
                response = "No data found for " + recipeID;
                System.out.println(response);
            }
        }
        return response;
    }

}