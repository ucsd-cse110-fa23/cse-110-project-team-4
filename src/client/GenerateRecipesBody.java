package client;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

/*
 * Class for the body of the Generate Recipes Window, containing the transcription of the voice recording.
 */
public class GenerateRecipesBody extends VBox{
    // private Image microphoneImage;
    // private ImageView imageView;
    private Label transcription;
    private HBox body;

    ScrollPane sp;

    public GenerateRecipesBody() {
        // imageView = new ImageView();

        //microphoneImage = new Image("./client/images/SeekPng.com_microphone-icon-png_458366.png");
        //imageView.setImage(microphoneImage);

        transcription = new Label("Transcribed Audio Will Go Here");

        transcription.setPadding(new Insets(10, 50, 10, 50));
        transcription.setWrapText(true);

        transcription.setMinHeight(200);
        transcription.setMinWidth(120);

        // imageView.setFitHeight(200);
        // imageView.setFitWidth(120);

        // HBox hb1 = new HBox();
        VBox vb1 = new VBox();

        // hb1.getChildren().add(imageView);
        vb1.getChildren().add(transcription);

        // sp = new ScrollPane();
        // sp.setContent(vb1);

        
        // hb1.setPadding(new Insets(10, 50, 10, 50));


        body = new HBox();
        body.getChildren().addAll(vb1);
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
