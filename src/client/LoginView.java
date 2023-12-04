package client;

import javafx.scene.layout.*;

public class LoginView extends BorderPane {
    private Header header;
    private Footer footer;
    private LoginBody loginBody;
    
    LoginViewController lvc;

    public LoginView(LoginViewController lvc) {
        this.lvc = lvc;
        
        // Initialize the header Object
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

    public LoginBody getLoginBody() {
        return this.loginBody;
    }

}
