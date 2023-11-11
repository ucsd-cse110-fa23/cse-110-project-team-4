package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URI;


public class Model {
    public String recipeRequest(String method, String uuid, String name, String details, String createdAt) {
        // Implement your HTTP request logic here and return the response

        try {
            String urlString = "http://localhost:8100/recipe";

            URL url = new URI(urlString).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);

            if (method.equals("GET")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(uuid + name);
                out.flush();
                out.close();
            } else if (method.equals("POST")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(name + ";" + details);
                out.flush();
                out.close();
            } else if (method.equals("PUT")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(uuid + ";" + name + ";" + details + ";" + "createdAt");
                out.flush();
                out.close();
            } else if (method.equals("DELETE")) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(uuid + name);
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
}