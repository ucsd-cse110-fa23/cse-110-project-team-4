package models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.json.JSONObject;

public class Model {
    public String performPOSTRequestForRecipe(String recipeName, String recipeContent, String recipeImage) {
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/recipe";
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            JSONObject createRecipeRequest = new JSONObject();
            createRecipeRequest.put("name", recipeName);
            createRecipeRequest.put("mealType", "breakfast");
            createRecipeRequest.put("details", recipeContent);
            createRecipeRequest.put("image", recipeImage);
            createRecipeRequest.put("userId", "65614b0c44879f466638921b");

            out.write(createRecipeRequest.toString());
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine();
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }

    public String recipeRequest(String method, String id, String name, String details, String image, Long createdAt) {
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/recipe";

            if(method.equals("GET") || method.equals("DELETE")) {
                if (id != null) {
                    urlString += "?=" + id;
                }
            }

            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (method.equals("GET")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(id + name);
                out.flush();
                out.close();
            } else if (method.equals("POST")) {
                JSONObject createRecipeRequest = new JSONObject();
                createRecipeRequest.put("name", name);
                createRecipeRequest.put("mealType", "Breakfast");
                createRecipeRequest.put("details", details);

                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(createRecipeRequest.toString());
                out.flush();
                out.close();
            } else if (method.equals("PUT")) {
                JSONObject editRecipeRequest = new JSONObject();
                editRecipeRequest.put("id", id);
                editRecipeRequest.put("name", name);
                editRecipeRequest.put("mealType", "breakfast");
                editRecipeRequest.put("details", details);
                editRecipeRequest.put("image", image);
                editRecipeRequest.put("createdAt", createdAt);

                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(editRecipeRequest.toString());
                out.flush();
                out.close();
            } else if (method.equals("DELETE")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(id + name);
                out.flush();
                out.close();
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine();
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }

    public String performGETRequestForList() {
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/recipeList?=65614b0c44879f466638921b";

            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine();
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }

    

    public String performGETRequestForRecipe(String query) {
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/recipe";
            if (query != null) {
                urlString += "?=" + query;
            }
            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine();
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }

    public String login(String username, String password) {
        try {
            String urlString = "http://localhost:8100/User";  // Replace with your server's authentication endpoint

            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Create a JSON object with username and password
            JSONObject loginRequest = new JSONObject();
            loginRequest.put("username", username);
            loginRequest.put("password", password);

            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(loginRequest.toString());
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = in.readLine();
            in.close();
            return response;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "Error: " + ex.getMessage();
        }
    }
}