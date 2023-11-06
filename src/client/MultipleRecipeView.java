package client;

import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class MultipleRecipeView extends BorderPane {
    private Header header;
    private Footer footer;
    private RecipeList recipeList;
    private Button addButton;

    MultipleRecipeView() {
        // Initialise the header Object
        header = new Header();
        header.setHeaderText("PantryPal");

        // Create a tasklist Object to hold the tasks
        recipeList = new RecipeList();

        // Initialise the Footer Object
        footer = new Footer();
        makeButton();
        addFooterButton();

        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        ScrollPane scroll = new ScrollPane(recipeList);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        this.setCenter(scroll);

        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);
        addListeners();
    }

    public void addListeners() {
        addButton.setOnAction(e -> {
            // go to generate recipe screen

            // temporary add recipe [Remove Later]
            Recipe recipe = new Recipe();
            recipeList.getChildren().add(recipe);
        });
    }

    private void addFooterButton() {
        footer.getChildren().add(addButton);
        footer.setAlignment(Pos.CENTER);
    }

    private void makeButton() {
        addButton = new Button("+");
    }

}