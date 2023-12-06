package client;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ServerErrorNotification {
    public static void alertNoConn(){
        Alert a = new Alert(AlertType.ERROR);
        a.setHeaderText("No Connection");
        a.setContentText("Please Try Again");
        //a.show();
        a.showAndWait();
    }
}
