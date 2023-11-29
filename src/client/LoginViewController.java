package client;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginViewController implements ViewController{
    
    private Stage stage;
    private int screenWidth;
    private int screenHeight;
    private LoginView loginView;
    MainViewController mvc;
    
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

    public void closeDisplay() {
        stage.close();
    }
    
    public void transitionToCreateAccount() {
        this.mvc.closeLoginOpenCreate();
    }

    public void transitionToMultiple(){
        this.mvc.closeLoginOpenMultiple();
    }
}
