package client;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginViewController {
<<<<<<< HEAD

    private Stage stage; // Changed from cavcStage to stage for clarity
    private int screenWidth; // Changed from screenSizeWidth for clarity
    private int screenHeight; // Changed from screenSizeHeight for clarity
    private LoginView loginView; // Use LoginView instead of CreateAccountView

    public LoginViewController(Stage primaryStage, int screenWidth, int screenHeight) {
        this.stage = primaryStage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        // Initialize the LoginView with this controller
        loginView = new LoginView(this);

        // Set the scene for the login view with the specified screen width and height
        stage.setScene(new Scene(loginView, this.screenWidth, this.screenHeight));
        stage.setTitle("PantryPal - Login"); // Set the title to include "Login"
    }

    public void display() {
        stage.show(); // Display the stage with the login view
    }

    public void closeDisplay() {
        stage.close(); // Close the stage if needed
    }

    
=======
    
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
>>>>>>> main
}
