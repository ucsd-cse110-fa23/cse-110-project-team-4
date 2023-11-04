package server;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;

public class RecipeHandler implements HttpHandler {

    private final RecipeRepository recipeRepository;

    public RecipeHandler(Map<String, String> data) {
        this.recipeRepository = new RecipeRepository(data);
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("GET")) {
              response = handleGet(httpExchange);
            } else if (method.equals("POST")) {
              response = handlePost(httpExchange);
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

    private JSONOb handleGet(HttpExchange httpExchange) throws IOException {
        String response = "Invalid GET request";
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        UUID uuid = UUID.fromString(query.substring(1));
        return this.recipeRepository.get(uuid);
    }     

}