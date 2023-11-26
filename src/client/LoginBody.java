package client;

import java.io.BufferedReader;
<<<<<<< HEAD
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
=======
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import javafx.geometry.Pos;
>>>>>>> main
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
<<<<<<< HEAD
import models.Model;
import server.repositories.UserRepository;
=======
>>>>>>> main

public class LoginBody extends VBox {
    Text usernameText;
    Text passwordText;
    TextField usernameField;
    PasswordField passwordField;
    Text usernameErrorMessage;
    Text passwordErrorMessage;
    CheckBox rememberMeCheckBox;
    Button loginButton;
    Text createAccountPrompt;
    Button createAccountButton;
    HBox createAccountRedirect;

    LoginViewController lvc;

    public LoginBody(LoginViewController lvc) {
        this.lvc = lvc;

        usernameText = new Text("Username: ");
        usernameText.setFont(Font.font(20));
        usernameField = new TextField();
        usernameField.setMaxWidth(250);
        usernameErrorMessage = new Text("");
        usernameErrorMessage.setFill(Color.RED);

        this.getChildren().add(usernameText);
        this.getChildren().add(usernameField);
        this.getChildren().add(usernameErrorMessage);

        passwordText = new Text("Password: ");
        passwordText.setFont(Font.font(20));
        passwordField = new PasswordField();
        passwordField.setMaxWidth(250);
        passwordErrorMessage = new Text("");
        passwordErrorMessage.setFill(Color.RED);

        this.getChildren().add(passwordText);
        this.getChildren().add(passwordField);
        this.getChildren().add(passwordErrorMessage);

        rememberMeCheckBox = new CheckBox("Remember me");
        this.getChildren().add(rememberMeCheckBox);

        loginButton = new Button("Login");
        loginButton.setOnAction(e -> performLogin());
        this.getChildren().add(loginButton);

        createAccountPrompt = new Text("Don't have an account? ");
        createAccountPrompt.setFont(Font.font(16));
        createAccountButton = new Button("Create one.");
<<<<<<< HEAD
        createAccountButton.setOnAction(e -> {
            // Transition to Create account page using main view controller
            // this.lvc.transitionToLogin
          });;
=======
        createAccountButton.setOnAction(e -> lvc.transitionToCreateAccount());
>>>>>>> main

        createAccountRedirect = new HBox(10, createAccountPrompt, createAccountButton);
        createAccountRedirect.setAlignment(Pos.CENTER);
        this.getChildren().add(createAccountRedirect);

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
    }

    private void performLogin() {
<<<<<<< HEAD
        // Retrieve the username and password entered by the user
        String username = usernameField.getText();
        String password = passwordField.getText();

        // You can add validation here to ensure that username and password are not empty
        if (username.isEmpty() || password.isEmpty()) {
            showErrorMessage("Username and password are required.");
            return;
        }

        // You can make an HTTP request to your backend server for authentication
        // Example using your Model class (make sure to adjust this based on your actual implementation):
        Model model = new Model();
        String response = model.login(username, password);

        // Check the response from the server
        if (response.startsWith("Error")) {
            showErrorMessage("An error occurred during login.");
        } else if (response.equals("Invalid Login Credentials")) {
            showErrorMessage("Invalid username or password.");
        } else {
            // Login successful, you can handle the successful login here
            // For example, you can navigate to a different view or update the UI accordingly
            // You might also store user session information, etc.
            showInfoMessage("Login successful. Welcome, " + username + "!");
        }
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Login Successful");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    

=======
    String username = usernameField.getText();
    String password = passwordField.getText();

    // Check if username or password fields are empty
    if (username.isEmpty() || password.isEmpty()) {
        usernameErrorMessage.setText(username.isEmpty() ? "Username cannot be empty" : "");
        passwordErrorMessage.setText(password.isEmpty() ? "Password cannot be empty" : "");
        return;
    }

    // Create the JSON object with the user credentials
    JSONObject credentials = new JSONObject();
    credentials.put("username", username);
    credentials.put("password", password);

    // Send POST request to server with user credentials
    try {
        URL url = new URL("http://localhost:8100/user");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");

        // Write JSON body to output stream
        try (OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream())) {
            out.write(credentials.toString());
        }

        // Read the response from the server
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        }

        // Check the server's response
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            // Parse the response to JSON
            JSONObject jsonResponse = new JSONObject(response.toString());
            
            // Here you should handle the JSON response, check if it contains a userId or the error message
            if (jsonResponse.has("userId")) {
                String userId = jsonResponse.getString("userId");
                // Login successful, proceed with the application logic with the userId
            } else {
                // Display the error message
                usernameErrorMessage.setText("Invalid Login Credentials");
                passwordErrorMessage.setText("Invalid Login Credentials");
            }
        } else {
            // Handle error response
            usernameErrorMessage.setText("An error occurred. Please try again.");
            passwordErrorMessage.setText("");
        }
    } catch (Exception e) {
        e.printStackTrace();
        usernameErrorMessage.setText("Failed to connect to the server.");
        passwordErrorMessage.setText("");
    }
}
>>>>>>> main

}
