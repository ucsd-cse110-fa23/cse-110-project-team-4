package client;

import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;



public class GenerateRecipesView extends BorderPane{

    private Header header;
    private Footer footer;
    Button startButton;
    Button stopButton;
    Button generateButton;
    GenerateRecipesBody grb;
    ScrollPane sp;

    AudioRecorder ar;


    GenerateRecipesView() {
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
        sp = new ScrollPane();
        sp.setContent(grb);

        sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);

        this.setCenter(grb);
        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane

        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);

        //ar = new AudioRecorder();

        this.addListeners();
    }

    public void addListeners() {
        generateButton.setOnAction(e -> {
            GenerateRecipeHandler grh = new GenerateRecipeHandler("Give me a dinner recipe for mashed potatoes.");
            this.grb.setRecipeText(grh.makeRequest());
        });

        startButton.setOnAction(e -> {
            ar = new AudioRecorder();
            Thread thread = new Thread(ar);
            thread.start();
            //ar.call();
        });

        stopButton.setOnAction(e -> {
            ar.finish();
            ar.cancel();
        });
    }
}