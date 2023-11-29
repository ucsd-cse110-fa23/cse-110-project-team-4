package server.handlers;

import com.sun.net.httpserver.*;
import java.io.*;
import java.util.*;

import org.json.JSONObject;

import server.repositories.UserRepository;

public class UserHandler implements HttpHandler {

    private final UserRepository userRepository;

    public UserHandler(UserRepository recipeRepository) {
        this.userRepository = recipeRepository;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        String response = "Request Received";
        String method = httpExchange.getRequestMethod();

        try {
            if (method.equals("POST")) {
                response = handlePost(httpExchange);
            } else if (method.equals("GET")) {
                response = handleGet(httpExchange);
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

    private JSONObject parseJSON(HttpExchange httpExchange) {
        InputStream inStream = httpExchange.getRequestBody();
        StringBuilder jsonBuff = new StringBuilder();
        String line = null;
        try {
            Scanner scanner = new Scanner(inStream);
            ;
            while ((line = scanner.nextLine()) != null)
                jsonBuff.append(line);
            scanner.close();
        } catch (Exception e) {
            /* error */ }

        // System.out.println("Request JSON string :" + jsonBuff.toString());
        // write the response here by getting JSON from jasonBuff.toString()

        JSONObject jsonObject = new JSONObject(jsonBuff.toString());
        return jsonObject;
    }

    private String handlePost(HttpExchange httpExchange) throws IOException {
        JSONObject createUserRequest = parseJSON(httpExchange);
        this.userRepository.createUser(createUserRequest);
        return "Successfully created user: " + createUserRequest.getString("username");
    }

    private String handleGet(HttpExchange httpExchange) {
        JSONObject loginRequest = parseJSON(httpExchange);
        return this.userRepository.login(loginRequest);
    }

}