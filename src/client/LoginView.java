package client;

import javafx.scene.layout.*;
import models.Model;

public class LoginView extends BorderPane {
    private Header header;
    private Footer footer;
    private Model model;
    private LoginBody loginBody;
    
    LoginViewController lvc;

    public LoginView(LoginViewController lvc) {
        this.lvc = lvc;
        
        // Initialize the header Object
        model = new Model();
        header = new Header();
        header.setHeaderText("Login");
        
        // Initialize the LoginBody Object
        loginBody = new LoginBody(lvc);
        
        // Initialize the Footer Object
        footer = new Footer();
        
        // Set up the layout for the login view
        this.setTop(header);
        this.setCenter(loginBody);
        this.setBottom(footer);
    }
}
