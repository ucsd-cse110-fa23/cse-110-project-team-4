package client.accountViews;

import client.MainViewController;
import client.ViewController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginViewController implements ViewController{
    
    private Stage stage;
    private int screenWidth;
    private int screenHeight;
    private LoginView loginView;
    MainViewController mvc;
    
    public LoginViewController(Stage stage, int screenWidth, int screenHeight, MainViewController mvc) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        
        // Initialize the LoginView
        loginView = new LoginView(this);
        stage.setScene(new Scene(loginView, this.screenWidth, this.screenHeight));
        stage.setTitle("PantryPal");

        this.mvc = mvc;
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

    public void login(String user) {
        this.mvc.setUser(user);
    }
}
