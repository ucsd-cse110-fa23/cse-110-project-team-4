package server;

import com.sun.net.httpserver.*;
import java.io.*;
import java.util.*;

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
        String response = "";
        ArrayList<String> recipeList = recipeRepository.getRecipeList();
        for(String s:recipeList){
            response += s + ";";
        }
        return response;
    }     

}