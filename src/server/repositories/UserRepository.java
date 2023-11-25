package server.repositories;

import server.User;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.json.JSONObject;

public class UserRepository {

    private static final String CONNECTION_URI = 
            "mongodb+srv://cse110-lab6:iLoveCSE110@cluster0.e0wpva4.mongodb.net/?retryWrites=true&w=majority";
    private MongoClient mongoClient = MongoClients.create(CONNECTION_URI);
    private MongoDatabase pantryPalDB = mongoClient.getDatabase("pantryPal");
    private MongoCollection<Document> userCollection = pantryPalDB.getCollection("user");


    public UserRepository() {
        
    }

    public UserRepository(String test) {
        this.mongoClient = MongoClients.create(CONNECTION_URI);
        this.pantryPalDB = mongoClient.getDatabase("pantryPalTest");
        this.userCollection = pantryPalDB.getCollection("user");
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