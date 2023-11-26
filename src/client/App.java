package client;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Setting the Layout of the Window- Should contain a Header, Footer and the
        // TaskList
        // new MainViewController();

        int tempSsw = 500;
        int tempSsh = 700;
        Stage tempStage = new Stage();
        CreateAccountViewController cavc = new CreateAccountViewController(tempStage, tempSsw, tempSsh);
        cavc.display();
    }
}