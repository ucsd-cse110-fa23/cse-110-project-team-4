package server;

import com.sun.net.httpserver.*;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import models.*;

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

        //Sending back response to the client
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outStream = httpExchange.getResponseBody();
        outStream.write(response.getBytes());
        outStream.close();
       
    }

    private boolean isUUID(String s){
        Pattern UUID_REGEX =
            Pattern.compile("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");

        return UUID_REGEX.matcher(s).matches();
    }

    private String handleGet(HttpExchange httpExchange) throws IOException {

        InputStream inStream = httpExchange.getRequestBody();
        Scanner scanner = new Scanner(inStream);
        String query = scanner.nextLine();
        scanner.close();

        Recipe r;
        if (isUUID(query)) {
            r = this.recipeRepository.getRecipe(UUID.fromString(query));    
        } else {
            r = this.recipeRepository.getRecipe(query);    
        }
        return r.toString();
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