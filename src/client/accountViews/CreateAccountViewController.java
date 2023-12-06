package client.accountViews;

import client.MainViewController;
import client.ViewController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateAccountViewController implements ViewController {

    Stage cavcStage;
    int screenSizeWidth;
    int screenSizeHeight;
    MainViewController mvc;
    CreateAccountView cav;

    public CreateAccountViewController(Stage primaryStage, int screenSizeWidth, int screenSizeHeight, MainViewController mvc) {
        this.cavcStage = primaryStage;
        this.screenSizeWidth = screenSizeWidth;
        this.screenSizeHeight = screenSizeHeight;
        this.mvc = mvc;
        this.cav = new CreateAccountView(this);
        cavcStage.setScene(new Scene(cav, this.screenSizeWidth, this.screenSizeHeight));
        cavcStage.setTitle("PantryPal");
    }

    public void display() {
        cavcStage.show();
    }

    public void closeDisplay() {
        cavcStage.close();
    }

    public void transitionToLogin(){
        this.mvc.closeCreateOpenLogin();
    }
}
