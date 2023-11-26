package client;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import models.Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;

import java.util.*;

public class MultipleRecipeView extends BorderPane {
    private Header header;
    private Footer footer;
    private RecipeListBody recipeListBody;
    private List<JSONObject> recipeArrayList;
    private Button addButton;
    private ComboBox<String> filterDropdown;
    private ComboBox<String> sortDropdown;
    private String currFilter;
    private Model model;

    ObservableList<String> filterOptions = FXCollections.observableArrayList(
            "Breakfast",
            "Lunch",
            "Dinner",
            "No Filter");
    ObservableList<String> sortOptions = FXCollections.observableArrayList(
            "Chronologically ASC",
            "Chronologically DSC");
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
        recipeArrayList = new ArrayList<JSONObject>();
        // System.out.println(response);
        if (response != null) {

            JSONArray recipeList = new JSONArray(response);
            for (int i = 0, size = recipeList.length(); i < size; i++) {
                JSONObject recipe = recipeList.getJSONObject(i);
                recipeArrayList.add(recipeList.getJSONObject(i));
                createRecipeButton(recipe);
            }
        }
        addListenersForButtons();
    }

    public void generateButtons() {
        recipeListBody.getChildren().clear();
        for (int i = 0, size = recipeArrayList.size(); i < size; i++) {
            JSONObject recipe = recipeArrayList.get(i);
            if (this.currFilter == null
                    || this.currFilter.equals("No Filter")
                    || recipe.getString("mealType").equals(this.currFilter)) {
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
        footer.getChildren().add(sortDropdown);
        footer.getChildren().add(addButton);
        footer.getChildren().add(filterDropdown);
        footer.setAlignment(Pos.CENTER);

    }

    public void makeButtons() {
        addButton = new Button("+");
        filterDropdown = new ComboBox<>(filterOptions);
        filterDropdown.setPromptText("No Filter");
        filterDropdown.setMaxWidth(150);

        VBox rootFilter = new VBox();
        rootFilter.getChildren().add(filterDropdown);
        filterDropdown.showingProperty().addListener((observable, oldValue, showing) -> {
            if (showing) {
                double comboBoxHeight = filterDropdown.getHeight();
                filterDropdown.setTranslateY(-comboBoxHeight * 4);
            } else {
                filterDropdown.setTranslateY(0);
            }
        });

        sortDropdown = new ComboBox<>(sortOptions);
        sortDropdown.setPromptText("Chronologically ASC");
        sortDropdown.setMaxWidth(150);

        VBox rootSort = new VBox();
        rootSort.getChildren().add(filterDropdown);
        filterDropdown.showingProperty().addListener((observable, oldValue, showing) -> {
            if (showing) {
                double comboBoxHeight = filterDropdown.getHeight();
                filterDropdown.setTranslateY(-comboBoxHeight * 4);
            } else {
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
                this.currFilter = mealType;
                generateButtons();
            } else {
                System.out.println("No item selected.");
            }
        });

        sortDropdown.setOnAction(e -> {
            String sortOption = sortDropdown.getValue();
            System.out.println(sortOption);
            if (sortOption != null) {
                System.out.println("Selected Item: " + sortOption);

                if (sortOption == "Chronologically ASC") {
                    handleSortByTime(false);
                } else if (sortOption == "Chronologically DSC") {
                    handleSortByTime(true);
                }
                // do sort alphabetically here
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

    public void handleSortByTime(Boolean reverseOrder) {
        Collections.sort(recipeArrayList, new Comparator<JSONObject>() {
            // You can change "Name" with "ID" if you want to sort by ID
            @Override
            public int compare(JSONObject a, JSONObject b) {
                Long valA;
                Long valB;

                valA = a.getLong("createdAt");
                valB = b.getLong("createdAt");

                int comp = Long.compare(valA, valB);

                return comp;
            }
        });

        if (reverseOrder) {
            Collections.reverse(recipeArrayList);
        }

        generateButtons();
    }

    // public void handleSort(String key, Boolean reverseOrder) {
    // Collections.sort(recipeArrayList, new Comparator<JSONObject>() {
    // // You can change "Name" with "ID" if you want to sort by ID
    // @Override
    // public int compare(JSONObject a, JSONObject b) {
    // String valA = new String();
    // String valB = new String();

    // try {
    // valA = a.getString(key);
    // valB = b.getString(key);
    // } catch (JSONException e) {
    // // do something
    // }

    // int comp = valA.compareTo(valB);

    // return comp;
    // }
    // });

    // if (reverseOrder) {
    // Collections.reverse(recipeArrayList);
    // }

    // }

}