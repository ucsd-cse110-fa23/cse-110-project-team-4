package server;

import com.sun.net.httpserver.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;
import java.util.concurrent.*;
import models.Recipe;

public class Server {

    // initialize server port and hostname
    private static final int SERVER_PORT = 8100;
    private static final String SERVER_HOSTNAME = "localhost";

    public static void main(String[] args) throws IOException {

        // create a thread pool to handle requests
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        HashMap<String, Recipe> data = new HashMap<>();

        // create a server
        HttpServer server = HttpServer.create(
            new InetSocketAddress(SERVER_HOSTNAME, SERVER_PORT),
            0
        );

        server.createContext("/recipe", new RecipeHandler(data));
        server.createContext("/RecipeList", new RecipeListHandler(data));
        server.setExecutor(threadPoolExecutor);
        server.start();
        
        System.out.println("Server started on port" + SERVER_PORT);
    }
}
