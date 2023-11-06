package server;

import com.sun.net.httpserver.*;
import java.io.*;
import java.util.*;
import models.*;

public class RecipeHandler implements HttpHandler {

    private final RecipeRepository recipeRepository;

    public RecipeHandler(Map<String, Recipe> data) {
        this.recipeRepository = new RecipeRepository(data);
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("POST")) {
              response = handlePost(httpExchange);
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

    private String handlePost(HttpExchange httpExchange) throws IOException {
        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String[] postData = scanner.nextLine().split(",");
        Recipe r = new Recipe(postData[0], postData[1]);
        this.recipeRepository.createRecipe(r);
        scanner.close();
        return r.toString();
    }     

}