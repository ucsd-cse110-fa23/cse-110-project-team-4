package client;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.Model;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

public class MultipleRecipeView extends BorderPane {
    private Header header;
    private Footer footer;
    private RecipeListBody recipeListBody;
    private JSONArray recipeList;
    private Button addButton;
    private ComboBox<String> filterDropdown;
    private Model model;

    ObservableList<String> options = FXCollections.observableArrayList(
            "Breakfast",
            "Lunch",
            "Dinner",
            "None");
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
            recipeList = new JSONArray(response);
            for (int i = 0, size = recipeList.length(); i < size; i++) {
                JSONObject recipe = recipeList.getJSONObject(i);

                createRecipeButton(recipe);
            }
        }
        addListenersForButtons();
    }

    public void handleFilter(String mealType) {
        recipeListBody.getChildren().clear();
        for (int i = 0, size = recipeList.length(); i < size; i++) {
            JSONObject recipe = recipeList.getJSONObject(i);
            if (mealType.equals(null)
                    || mealType.equals("None")
                    || recipe.getString("mealType").equals(mealType)) {
                createRecipeButton(recipe);
            }
        }
        addListenersForButtons();
    }

    public void createRecipeButton(JSONObject recipe) {
        RecipeButton recipeButton = new RecipeButton();
        System.out.println(recipe.getString("name"));
        recipeButton.setUUID(recipe.getString("id"));
        recipeButton.setRecipeName(recipe.getString("name"));
        recipeButton.setMealType(recipe.getString("mealType"));
        recipeListBody.getChildren().add(recipeButton);
    }

    public void addFooterButton() {
        footer.getChildren().add(addButton);
        footer.getChildren().add(filterDropdown);
        footer.setAlignment(Pos.CENTER);

    }

    public void makeButtons() {
        addButton = new Button("+");
        filterDropdown = new ComboBox<>(options);
        filterDropdown.setPromptText("Filter by Meal Type");

        VBox root = new VBox();
        root.getChildren().add(filterDropdown);
        filterDropdown.showingProperty().addListener((observable, oldValue, showing) -> {
            if (showing) {
                // If the ComboBox dropdown is showing, adjust its position
                double comboBoxHeight = filterDropdown.getHeight();
                filterDropdown.setTranslateY(-comboBoxHeight * 4);
            } else {
                // Reset the translation when the dropdown is hidden
                filterDropdown.setTranslateY(0);
            }
        });
    }

    public void addListeners() {
        addButton.setOnAction(e -> {

            // Recipe recipe = new Recipe();
            // recipe.setRecipeName("Recipe Name");
            // recipeList.getChildren().add(recipe);
            this.mrvc.transitionToGenerate();
        });

        filterDropdown.setOnAction(e -> {
            String mealType = filterDropdown.getValue();
            System.out.println(mealType);
            if (mealType != null) {
                System.out.println("Selected Item: " + mealType);
                handleFilter(mealType);
            } else {
                System.out.println("No item selected.");
            }
        });

    }

    public void addListenersForButtons() {
        for (int i = 0; i < recipeListBody.getChildren().size(); i++) {
            if (this.recipeListBody.getChildren().get(i) instanceof RecipeButton) {
                RecipeButton rb = (RecipeButton) this.recipeListBody.getChildren().get(i);
                rb.button.setOnAction(e -> {
                    // System.out.println(rb.recipeName.getText());
                    // mrvc.mvc.closeMultipleOpenDetailed(rb.uuid);
                    this.mrvc.transitionToDetailed(rb.uuid);
                });
            }
        }
    }

}