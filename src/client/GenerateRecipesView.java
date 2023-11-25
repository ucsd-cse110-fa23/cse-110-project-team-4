package client;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

/*
 * Class for all the contents of the Generate Recipes Window -- Header, body, and footer with buttons and their listeners
 */

public class GenerateRecipesView extends BorderPane {

    private Header header;
    private Footer footer;

    private Button startButton;
    private Button stopButton;
    private Button generateButton;
    private GenerateRecipesBody grb;

    private GenerateRecipesLogic grl;

    private GenerateRecipesViewController grvc;

    public GenerateRecipesView(GenerateRecipesViewController grvc) {
        // Initialise the header Object
        header = new Header();
        header.setHeaderText("Generate Recipes");

        // Create a tasklist Object to hold the tasks

        // Initialise the Footer Object
        footer = new Footer();

        startButton = new Button("START");
        startButton.setPadding(new Insets(10, 10, 10, 10));

        stopButton = new Button("STOP");
        stopButton.setPadding(new Insets(10, 10, 10, 10));

        generateButton = new Button("GENERATE");
        generateButton.setPadding(new Insets(10, 10, 10, 10));

        HBox buttons = new HBox();
        buttons.getChildren().addAll(startButton, stopButton, generateButton);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(15);

        footer.getChildren().add(buttons);
        footer.setAlignment(Pos.CENTER);

        this.grb = new GenerateRecipesBody();

        this.setCenter(grb);
        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane

        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);

        this.grl = new GenerateRecipesLogic();

        this.grvc = grvc;
        this.addListeners();
    }

    public void addListeners() {
        generateButton.setOnAction(e -> {
            String[] recipeContent = this.grl.performGenerateButtonAction();
            if (recipeContent != null) {
                this.grvc.exportRecipeToDetailed(recipeContent[0], recipeContent[1], recipeContent[2]);
                this.grvc.transitionToDetailed();
            }
            else {
                this.grb.setTranscription("Cannot Generate Recipe Without Valid Voice Prompt");
            }
        });

        startButton.setOnAction(e -> {
            this.grl.performStartButtonAction();
        });

        stopButton.setOnAction(e -> {
            String prompt = this.grl.performStopButtonAction();
            if(prompt != null) {
                this.grb.setTranscription(prompt);
            }
        });

    }
}