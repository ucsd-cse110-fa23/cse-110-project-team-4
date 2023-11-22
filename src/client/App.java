package client;

import javafx.application.Application;
import javafx.stage.Stage;
import client.controllers.MainViewController;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        
        // Setting the Layout of the Window- Should contain a Header, Footer and the
        // TaskList
        new MainViewController();

    }
}