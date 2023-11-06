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
    Label transcription;
    HBox body;

    ScrollPane sp;

    GenerateRecipesBody() {
        imageView = new ImageView();
        microphoneImage = new Image("./client/images/SeekPng.com_microphone-icon-png_458366.png");
        imageView.setImage(microphoneImage);

        transcription = new Label("Transcribed Audio Will Go Here");

        transcription.setPadding(new Insets(10, 50, 10, 50));
        transcription.setWrapText(true);

        transcription.setMinHeight(300);
        transcription.setMinWidth(200);

        imageView.setFitHeight(300);
        imageView.setFitWidth(200);

        HBox hb1 = new HBox();
        VBox vb1 = new VBox();

        hb1.getChildren().add(imageView);
        vb1.getChildren().add(transcription);

        sp = new ScrollPane();
        sp.setContent(vb1);

        
        hb1.setPadding(new Insets(10, 50, 10, 50));


        body = new HBox();
        body.getChildren().addAll(hb1,sp);
        body.setAlignment(Pos.CENTER);

        this.getChildren().add(body);
        this.setAlignment(Pos.CENTER);
    }

    public String getTranscription() {
        return this.transcription.getText();
    }

    public void setTranscription(String newTranscription) {
        this.transcription.setText(newTranscription);
    }

    public VBox getTranscriptionVBox()  {
        return (VBox) this.body.getChildren().get(1);
    }

}
