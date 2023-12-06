package server.repositories;

import server.User;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;

public class UserRepository {

    private static final String CONNECTION_URI = "mongodb+srv://cse110-lab6:iLoveCSE110@cluster0.e0wpva4.mongodb.net/?retryWrites=true&w=majority";
    private MongoClient mongoClient = MongoClients.create(CONNECTION_URI);
    private MongoDatabase pantryPalDB = mongoClient.getDatabase("pantryPal");
    private MongoCollection<Document> userCollection = pantryPalDB.getCollection("user");

    public UserRepository() {
    }

    // change for testing
    public UserRepository(String test) {
        this.mongoClient = MongoClients.create(CONNECTION_URI);
        this.pantryPalDB = mongoClient.getDatabase("pantryPalTest");
        this.userCollection = pantryPalDB.getCollection("user");
    }

    // validates a create request, checks non empty and non duplicate
    public boolean validCreateRequest(JSONObject createUserJSON) {
        String username = createUserJSON.getString("username");
        String password = createUserJSON.getString("password");
        if (username.equals("") || password.equals("")) {
            return false;
        }else if (userCollection.find(new Document("username", username)).first() != null){
            return false;
        }
        return true;
    }
    
    // creates user if valid
    public User createUser(JSONObject createUserJSON) {
        if (!validCreateRequest(createUserJSON)) return null;
        
        User user = new User(createUserJSON);
        System.out.println(user.toJSON().toString());
        Document recipeDoc = new Document("_id", user.id);
        recipeDoc.append("username", user.username)
                .append("password", user.password);

        userCollection.insertOne(recipeDoc);
        return user;
    }

    // returns login token
    public String login(JSONObject loginRequest) {
        String username = loginRequest.getString("username");
        String password = loginRequest.getString("password");
        Bson filter = and(eq("username", username), eq("password", password));
        Document userDocument = userCollection.find(filter).first();
        if (userDocument != null) {
            return userDocument.getObjectId("_id").toString();
        } else {
            return "Invalid Login Credentials";
        }
    }

}