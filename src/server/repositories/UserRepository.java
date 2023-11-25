package server.repositories;

import server.User;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONObject;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class UserRepository {

    private static final String CONNECTION_URI = 
            "mongodb+srv://cse110-lab6:iLoveCSE110@cluster0.e0wpva4.mongodb.net/?retryWrites=true&w=majority";
    private final MongoClient mongoClient = MongoClients.create(CONNECTION_URI);
    private final MongoDatabase pantryPalDB = mongoClient.getDatabase("pantryPal");
    private MongoCollection<Document> userCollection = pantryPalDB.getCollection("user");


    public UserRepository() {
        
    }

    public UserRepository(String collection) {
        this.userCollection = pantryPalDB.getCollection(collection);
    }

    // public ArrayList<String> getRecipeList() {
    //     ArrayList<String> recipeList = new ArrayList<String>();

    //     for (Recipe recipe : data.values())
    //         recipeList.add(recipe.uuid + "," + recipe.name);

    //     Collections.sort(recipeList,
    //             (a, b) -> (Long.compare(this.getRecipe(a).createdAt, this.getRecipe(b).createdAt) * -1));

    //     return recipeList;
    // }

    public User createUser(JSONObject createUserJSON) {
        User user = new User(createUserJSON);
        System.out.println(user.toJSON().toString());
        Document recipeDoc = new Document("_id", user.id);
        recipeDoc.append("username", user.username)
                .append("password", user.password);

        userCollection.insertOne(recipeDoc);
        return user;
    }

}