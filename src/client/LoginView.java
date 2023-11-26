package client;

import javafx.scene.layout.*;
import models.Model;

public class LoginView extends BorderPane {
    private Header header;
    private Footer footer;
    private Model model;
    private LoginBody loginBody;

    private LoginViewController lvc; 

    public LoginView(LoginViewController lvc) {
        this.lvc = lvc;

        // Initialise the header Object
        model = new Model();
        header = new Header();
        header.setHeaderText("Login"); // Set the header text to "Login"

        // Initialise the LoginBody Object
        loginBody = new LoginBody(lvc); // Initialize LoginBody with LoginViewController

        // Initialise the Footer Object
        footer = new Footer();

        // Set up the layout for the LoginView
        this.setTop(header);
        this.setCenter(loginBody);
        this.setBottom(footer);
    }
}
