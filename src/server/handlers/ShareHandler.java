package server.handlers;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.URI;

import server.repositories.RecipeRepository;

public class ShareHandler implements HttpHandler {

    private final RecipeRepository recipeRepository;

    public ShareHandler(RecipeRepository recipeRepository) {
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
            String recipeID = query.substring(query.indexOf("=") + 1);
            
            response = this.recipeRepository.shareRecipe(recipeID); // Retrieve data
        }
        return response;
    }     
}