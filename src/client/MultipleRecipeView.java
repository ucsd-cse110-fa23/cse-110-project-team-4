package client;

import javafx.scene.text.*;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Model;
import javafx.geometry.Pos;
import client.RecipeButton;

public class MultipleRecipeView extends BorderPane {
    private Header header;
    private Footer footer;
    private RecipeListBody recipeListBody;
    private String[] recipeList;
    private Button addButton;
    private Model model;

    MultipleRecipeViewController mrvc;

    MultipleRecipeView(MultipleRecipeViewController mrvc) {
        model = new Model();

        // Initialise the header Object
        header = new Header();
        header.setHeaderText("PantryPal");

        // Create a recipeList Object to hold the recipes
        recipeListBody = new RecipeListBody();

        // Initialise the Footer Object
        footer = new Footer();
        makeButtons();
        addFooterButton();

        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        ScrollPane scroll = new ScrollPane(recipeListBody);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        this.setCenter(scroll);

        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);

        this.mrvc = mrvc;

        // Call event listner for the button
        addListeners();
    }

    public void loadRecipeList() {
        recipeListBody.getChildren().clear();
        String response = model.performGETRequestForList();
        // System.out.println(response);
        if (response != null) {
            recipeList = response.split(";");
            for (String name : recipeList) {
                RecipeButton recipeButton = new RecipeButton();
                System.out.println(name);
                recipeButton.setUUID(name.split(",")[0]);
                recipeButton.setRecipeName(name.split(",")[1]);
                recipeListBody.getChildren().add(recipeButton);
            }
        }
        addListenersForButtons();

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

            // Recipe recipe = new Recipe();
            // recipe.setRecipeName("Recipe Name");
            // recipeList.getChildren().add(recipe);
            this.mrvc.transitionToGenerate();
        });
    }

    public void addListenersForButtons() {
        for (int i = 0; i < recipeListBody.getChildren().size(); i++) {
            if (this.recipeListBody.getChildren().get(i) instanceof RecipeButton) {
                RecipeButton rb = (RecipeButton) this.recipeListBody.getChildren().get(i);
                rb.recipeName.setOnAction(e -> {
                    System.out.println(rb.recipeName.getText());
                    // mrvc.mvc.closeMultipleOpenDetailed(rb.uuid);
                    this.mrvc.transitionToDetailed(rb.uuid);
                });
            }
        }
    }

}