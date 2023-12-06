package client.generateRecipes;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

/*
 * Class for the body of the Generate Recipes Window, containing the transcription of the voice recording.
 */
public class GenerateRecipesBody extends VBox{
    private Label transcription;
    private HBox body;

    ScrollPane sp;

    public GenerateRecipesBody() {

        transcription = new Label("Transcribed Audio Will Go Here");

        transcription.setPadding(new Insets(10, 50, 10, 50));
        transcription.setWrapText(true);

        transcription.setMinHeight(200);
        transcription.setMinWidth(120);

        VBox vb1 = new VBox();
        vb1.getChildren().add(transcription);

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
