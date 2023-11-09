package client;

import javafx.scene.text.*;

import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class GenerateRecipesView extends BorderPane {

    private Header header;
    private Footer footer;

    private Button startButton;
    private Button stopButton;
    private Button generateButton;
    private GenerateRecipesBody grb;

    private AudioRecorder ar;
    private AudioTranscriber at;
    private String prompt;

    private boolean validPromptFlag;
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

        validPromptFlag = false;

        // ar = new AudioRecorder();
        this.grvc = grvc;

        this.addListeners();
    }

    public void addListeners() {
        generateButton.setOnAction(e -> {
            if (!validPromptFlag) {
                String cannotGenerateNow = "Cannot Generate Recipe Without Valid Voice Prompt";
                this.grb.setTranscription(cannotGenerateNow);
            } else {
                try {
                    GenerateRecipeHandler grh = new GenerateRecipeHandler(this.prompt + 
                    "in the format of title followed by ingredients, and then instructions for a recipe.");
                    String recipeInfo = grh.makeRequest();
                    String[] recipeInfoSplit = recipeInfo.split("\n");
                    String recipeName = recipeInfoSplit[2];
                    String recipeDetails = String.join("\n", Arrays.copyOfRange(recipeInfoSplit, 3, recipeInfoSplit.length));
                    this.grvc.mvc.dvc.drv.getDetailedRecipeInfoBody().setRecipeNAme(recipeName);
                    this.grvc.mvc.dvc.drv.getDetailedRecipeInfoBody().setRecipeContext(recipeDetails);
                    this.grvc.mvc.closeGenerateOpenDetailed();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        startButton.setOnAction(e -> {
            ar = new AudioRecorder();
            // ar.startRecording();
            Thread thread = new Thread(ar);
            thread.start();
        });

        stopButton.setOnAction(e -> {
            ar.finish();
            ar.cancel();

            at = new AudioTranscriber("src/client/audio/RecordAudio.wav");
            try {
                this.prompt = at.generateTranscription();
                if (prompt.contains("dinner") || prompt.contains("Dinner") || prompt.contains("breakfast") ||
                        prompt.contains("Breakfast") || prompt.contains("lunch") || prompt.contains("Lunch")) {
                    this.validPromptFlag = true;
                    this.grb.setTranscription(prompt);
                } else {
                    String noMealType = "Please Specify A Meal Type In Your Voice Prompt";
                    grb.setTranscription(noMealType);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

    }

    public boolean getValidPromptFlag() {
        return this.validPromptFlag;
    }
}