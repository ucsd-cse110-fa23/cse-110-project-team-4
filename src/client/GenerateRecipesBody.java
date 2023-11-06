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

    ScrollPane sp;

    GenerateRecipesBody() {
        imageView = new ImageView();
        microphoneImage = new Image("./client/images/SeekPng.com_microphone-icon-png_458366.png");
        imageView.setImage(microphoneImage);

        recipeText = new Label("Generated Recipe Will Go Here");

        recipeText.setPadding(new Insets(10, 50, 10, 50));
        recipeText.setWrapText(true);

        recipeText.setMinHeight(300);
        recipeText.setMinWidth(200);

        imageView.setFitHeight(300);
        imageView.setFitWidth(200);

        HBox hb1 = new HBox();
        VBox vb1 = new VBox();

        hb1.getChildren().add(imageView);
        vb1.getChildren().add(recipeText);

        sp = new ScrollPane();
        sp.setContent(vb1);

        
        hb1.setPadding(new Insets(10, 50, 10, 50));


        body = new HBox();
        body.getChildren().addAll(hb1,sp);
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

    public VBox getRecipeTextVBox()  {
        return (VBox) this.body.getChildren().get(1);
    }

}
