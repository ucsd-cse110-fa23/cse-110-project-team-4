package client;

import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;



public class MultipleRecipeView extends BorderPane{
    private Header header;
    private Footer footer;

    MultipleRecipeView() {
        // Initialise the header Object
        header = new Header();
        header.setHeaderText("PantryPal");

        // Create a tasklist Object to hold the tasks

        // Initialise the Footer Object
        footer = new Footer();


        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane

        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);
    }
}