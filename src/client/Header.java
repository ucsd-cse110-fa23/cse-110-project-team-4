package client;

import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

class Header extends HBox {

    Header() {
        this.setPrefSize(500, 60); // Size of the header
        this.setStyle("-fx-background-color: #F0F8FF;");

        
    }

    public void setHeaderText(String headerText){
        Text titleText = new Text(headerText); // Text of the Header
        titleText.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
        this.getChildren().add(titleText);
        this.setAlignment(Pos.CENTER); // Align the text to the Center
    }
    
}