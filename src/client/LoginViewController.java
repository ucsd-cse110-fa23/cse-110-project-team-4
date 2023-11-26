package client;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginViewController {
    
    private Stage stage;
    private int screenWidth;
    private int screenHeight;
    private LoginView loginView;
    
    public LoginViewController(Stage stage, int screenWidth, int screenHeight) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        
        // Initialize the LoginView
        loginView = new LoginView(this);
        stage.setScene(new Scene(loginView, screenWidth, screenHeight));
        stage.setTitle("PantryPal");
    }

    public void display() {
        stage.show();
    }
    
    public void transitionToCreateAccount() {
        // You need to implement the logic to switch to the Create Account View
        // For example:
        // stage.setScene(new Scene(new CreateAccountView(new CreateAccountViewController(stage, screenWidth, screenHeight))));
        // stage.show();
    }
}
