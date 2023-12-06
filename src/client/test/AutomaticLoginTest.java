package client.test;

import org.junit.jupiter.api.Test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import server.User;
import server.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.bson.Document;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AutomaticLoginTest {

    private static final String CONNECTION_URI = "mongodb+srv://cse110-lab6:iLoveCSE110@cluster0.e0wpva4.mongodb.net/?retryWrites=true&w=majority";
    private final MongoClient mongoClient = MongoClients.create(CONNECTION_URI);
    private final MongoDatabase pantryPalDB = mongoClient.getDatabase("pantryPalTest");
    private MongoCollection<Document> testUserCollection = pantryPalDB.getCollection("user");

    UserRepository userRepository = new UserRepository("test");

    User user1, user2;

    void insertUser(User user) {
        Document userDoc = new Document("_id", user.id);
        userDoc.append("username", user.username)
                .append("password", user.password);

        testUserCollection.insertOne(userDoc);
    }

    @BeforeEach
    void seedData() {
        testUserCollection.deleteMany(new Document());

        user1 = new User("65614b0c44879f466638921b", "maxwn04", "passw0rd!");
        user2 = new User("65614b0c44879f477738921b", "arvinz", "pa55w0rd#");
        insertUser(user1);
        insertUser(user2);
    }

    @Test
    void testCreateAutomaticLoginToken() {
        try {
            FileWriter fw = new FileWriter("src\\client\\AutomaticLoginToken\\AutomaticLoginTokenTest.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            String username = user1.toJSON().getString("username");
            String password = user1.toJSON().getString("password");

            bw.write(username);
            bw.newLine();
            bw.write(password);
            bw.newLine();
            bw.write("yes");
            bw.close();

            FileReader fr = new FileReader("src\\client\\AutomaticLoginToken\\AutomaticLoginTokenTest.txt");
            BufferedReader br = new BufferedReader(fr);
            String readUsername = br.readLine();
            String readPassword = br.readLine();
            String automaticLogin = br.readLine();
            br.close();

            assertEquals(username, readUsername);
            assertEquals(password, readPassword);
            assertEquals("yes", automaticLogin);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testPerformAutomaticLogin() {
        try {
            FileReader fr = new FileReader("src\\client\\AutomaticLoginToken\\AutomaticLoginTokenTest.txt");
            BufferedReader br = new BufferedReader(fr);
            String readUsername = br.readLine();
            String readPassword = br.readLine();
            String automaticLogin = br.readLine();
            fr.close();
            br.close();

            assertEquals("yes", automaticLogin);

            JSONObject loginRequest = new JSONObject();
            loginRequest.put("username", readUsername);
            loginRequest.put("password", readPassword);

            String response = userRepository.login(loginRequest);
            assertEquals(user1.id.toString(), response);
            assertNotEquals("Invalid Login Credentials", response);

            FileWriter fw = new FileWriter("src\\client\\AutomaticLoginToken\\AutomaticLoginTokenTest.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("no");
            bw.close();

            FileReader fr2 = new FileReader("src\\client\\AutomaticLoginToken\\AutomaticLoginTokenTest.txt");
            BufferedReader br2 = new BufferedReader(fr);
            br.readLine();
            br.readLine();
            String automaticLogin2 = br.readLine();
            fr2.close();
            br2.close();

            assertNotEquals("yes", automaticLogin2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
