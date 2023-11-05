package client;

import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;


public class GenerateRecipesBody extends VBox{
    Image microphoneImage;
    ImageView imageView;
    Label recipeText;
    HBox body;

    GenerateRecipesBody() {
        imageView = new ImageView();
        microphoneImage = new Image("./client/images/SeekPng.com_microphone-icon-png_458366.png");
        imageView.setImage(microphoneImage);

        recipeText = new Label("Generated Recipe Will Go Here");

        recipeText.setPadding(new Insets(100, 50, 10, 50));
        recipeText.setWrapText(true);

        imageView.setFitHeight(300);
        imageView.setFitWidth(200);

        HBox hb1 = new HBox();
        HBox hb2 = new HBox();

        hb1.getChildren().add(imageView);
        hb2.getChildren().add(recipeText);
        
        hb1.setPadding(new Insets(10, 50, 10, 50));

        body = new HBox();
        body.getChildren().addAll(hb1, hb2);
        body.setAlignment(Pos.CENTER);

        this.getChildren().add(body);
        this.setAlignment(Pos.CENTER);
    }

    public String getRecipeText() {
        return this.recipeText.getText();
    }

    public void setRecipeText(String newRecipeText) {
        this.recipeText.setText(newRecipeText);
    }

}
