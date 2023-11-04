package server;

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import models.Recipe;

public class RecipeHandler implements HttpHandler {

    private final RecipeRepository recipeRepository;

    public RecipeHandler(Map<String, Recipe> data) {
        this.recipeRepository = new RecipeRepository(data);
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        JSONObject responseJSON = new JSONObject();
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("GET")) {
              responseJSON = handleGet(httpExchange);
            } else if (method.equals("POST")) {
              // response = handlePost(httpExchange);
            } else if (method.equals("PUT")) {
              // response = handlePut(httpExchange);
            } else if (method.equals("DELETE")) {
              // response = handleDelete(httpExchange);
            } else {
              throw new Exception("Not Valid Request Method");
            }
          } catch (Exception e) {
            System.out.println("An erroneous request");
            e.printStackTrace();
        }

        //Sending back response to the client
        httpExchange.getResponseHeaders().set("Content-Type", "application/json");
        httpExchange.sendResponseHeaders(200, responseJSON.toString().length());
        OutputStream outStream = httpExchange.getResponseBody();
        outStream.write(responseJSON.toString().getBytes());
        outStream.close();
       
    }

    private JSONObject handleGet(HttpExchange httpExchange) throws Exception {
        URI uri = httpExchange.getRequestURI();
        String query = uri.getRawQuery();
        String uuid = query.substring(1);
        return this.recipeRepository.getRecipe(uuid);
    }     

}