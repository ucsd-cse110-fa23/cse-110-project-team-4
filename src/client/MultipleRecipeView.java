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

class Recipe extends HBox {
    private Button recipeName;

    Recipe() {
        this.setPrefSize(500, 20); // sets size of recipe
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background

        recipeName = new Button("Recipe Name");
        recipeName.setPrefSize(Screen.getPrimary().getBounds().getWidth(), 20);
        recipeName.setStyle("-fx-background-color: #DAE5FF; -fx-border-width: 0;");
        recipeName.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().add(recipeName);

        addListeners();
    }

    public void addListeners() {
        recipeName.setOnAction(e -> {
            // go to detail recipe screen
        });
    }

}

class RecipeList extends VBox {
    RecipeList() {
        this.setSpacing(5); // sets spacing between recipe
        this.setPrefSize(500, 560);
        this.setStyle("-fx-background-color: #F0F8FF;");
    }
}

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