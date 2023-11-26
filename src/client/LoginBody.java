package client;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import models.Model;
import server.repositories.UserRepository;

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
        createAccountButton.setOnAction(e -> {
            // Transition to Create account page using main view controller
            // this.lvc.transitionToLogin
          });;

        createAccountRedirect = new HBox(10, createAccountPrompt, createAccountButton);
        createAccountRedirect.setAlignment(Pos.CENTER);
        this.getChildren().add(createAccountRedirect);

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
    }

    private void performLogin() {
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
    
        // Use the UserRepository to perform the login request
        UserRepository userRepository = new UserRepository();
        String result = userRepository.login(credentials);
    
        // Handle the result of the login attempt
        if (!"Invalid Login Credentials".equals(result)) {
            usernameErrorMessage.setText(""); 
            passwordErrorMessage.setText(""); 
            // Proceed with navigating to the next view or performing the next action
            handleSuccessfulLogin(result); 
        } else {
            // Login failed
            usernameErrorMessage.setText("Invalid Login Credentials");
            passwordErrorMessage.setText("Invalid Login Credentials");
        }
    }

    private void handleSuccessfulLogin(String userId) {
        // Implement actions to be taken after a successful login
        // For example, navigate to the next view or update UI
    }
    


}
