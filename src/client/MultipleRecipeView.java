package client;

import javafx.scene.control.*;
import javafx.scene.layout.*;
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

        // Create a recipeList Object to hold the recipes
        recipeList = new RecipeList();

        // Initialise the Footer Object
        footer = new Footer();
        makeButtons();
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

        // Call event listner for the button
        addListeners();
    }

    public void addFooterButton() {
        footer.getChildren().add(addButton);
        footer.setAlignment(Pos.CENTER);
    }

    public void makeButtons() {
        addButton = new Button("+");
    }

    public void addListeners() {
        addButton.setOnAction(e -> {
            Recipe recipe = new Recipe();
            recipe.setRecipeName("Recipe Name");
            recipeList.getChildren().add(recipe);
        });
    }

}