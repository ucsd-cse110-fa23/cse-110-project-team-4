package client;

import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import models.Model;
import javafx.geometry.Pos;

public class DetailedRecipeView extends BorderPane {
    private Header header;
    private Footer footer;
    private DetailedRecipeInfoBody detailedInfo;
    private Button backButton;
    private Button saveButton;
    private Button editButton;
    private Button deleteButton;
    private DetailedViewController vc;
    Model model;

    DetailedRecipeView(DetailedViewController vc) {
        model = new Model();
        this.vc = vc;
        // Initialise the header Object
        header = new Header();
        // Initialise the Footer Object
        footer = new Footer();

        this.makeButtons();
        this.addFooterButtons();

        this.addHeaderComponents();

        detailedInfo = new DetailedRecipeInfoBody();

        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        this.setCenter(detailedInfo);
        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);
        addListeners();
    }

    private void makeButtons() {
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        backButton = new Button("BACK");
        // backButton.setStyle(defaultButtonStyle);
        saveButton = new Button("SAVE");
        // saveButton.setStyle(defaultButtonStyle);
        editButton = new Button("EDIT");
        // editButton.setStyle(defaultButtonStyle);
        deleteButton = new Button("DELETE");
        // deleteButton.setStyle(defaultButtonStyle);
    }

    private void addFooterButtons() {
        footer.getChildren().addAll(deleteButton, editButton, saveButton);
        footer.setAlignment(Pos.CENTER);
    }

    private void addHeaderComponents() {
        HBox lBox = new HBox();
        lBox.getChildren().add(backButton);
        lBox.setAlignment(Pos.CENTER_LEFT);
        lBox.setMaxWidth(180);
        HBox.setHgrow(lBox, Priority.SOMETIMES);
        header.getChildren().add(lBox);
        header.setHeaderText("Detailed View");
        HBox rBox = new HBox();
        rBox.setMaxWidth(180);
        HBox.setHgrow(rBox, Priority.SOMETIMES);

        header.getChildren().add(rBox);
    }

    public void addListeners() {
        backButton.setOnAction(e -> {
            vc.mc.closeDetailedOpenMultiple();
        });

        editButton.setOnAction(e -> {
            detailedInfo.textFieldEditable(true);
        });

        saveButton.setOnAction(e -> {
            if (this.detailedInfo.getIsNewRecipe()) {
                model.performPOSTRequestForRecipe(this.detailedInfo.getRecipeName(),
                        this.detailedInfo.getRecipeContent());
            } else {
                model.recipeRequest("PUT", this.detailedInfo.getUUID(), this.detailedInfo.getRecipeName(),
                        this.detailedInfo.getRecipeContent(), this.detailedInfo.getCreatedAt());
            }

        });
    }

    public void getAndSetInfo(String uuid) {
        String data = model.performGETRequestForRecipe(uuid);
        System.out.println(data);
        String[] dataSplit = data.split(";");
        detailedInfo.setUUId(dataSplit[0]);
        detailedInfo.setRecipeNAme(dataSplit[1]);
        detailedInfo.setRecipeContext(dataSplit[2].replace("\\n", "\n"));
        detailedInfo.setCreatedAt(dataSplit[3]);
        detailedInfo.setIsNewRecipe(false);
    }

    public DetailedRecipeInfoBody getDetailedRecipeInfoBody() {
        return this.detailedInfo;
    }
}