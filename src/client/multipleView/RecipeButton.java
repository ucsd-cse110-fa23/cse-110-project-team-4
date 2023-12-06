package client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.*;
import javafx.stage.Screen;

public class RecipeButton extends HBox {
    HBox display;
    Text recipeName;
    Text recipeMealType;
    Button button;
    String uuid;

    public RecipeButton() {
        this.setSpacing(10);
        this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background
        this.setPrefSize(Screen.getPrimary().getBounds().getWidth(), 30);

        recipeName = new Text();
        recipeMealType = new Text();
        recipeName.setWrappingWidth(1);
        // Wrap texts in panes
        StackPane pane1 = new StackPane(recipeName);
        // Set wrapping width for recipeName text
        pane1.widthProperty().addListener((obs, oldVal, newVal) -> {
            // Subtract padding or any other adjustments from the new width value
            recipeName.setWrappingWidth(newVal.doubleValue() - 20);
        });
        pane1.setAlignment(Pos.CENTER_LEFT);
        pane1.setPadding(new Insets(3, 1, 3, 1));

        StackPane pane2 = new StackPane(recipeMealType);
        pane2.setPadding(new Insets(3, 1, 3, 1));

        // HBox
        HBox display = new HBox(pane1, pane2);
        HBox.setHgrow(pane1, Priority.SOMETIMES); // pane1 takes as much space as possible
        HBox.setHgrow(pane2, Priority.SOMETIMES);  // pane2 takes only the space it needs

        button = new Button();
        button.setStyle("-fx-background-color: #DAE5FF; -fx-border-width: 0;"); // set background color of texfield
        button.setPrefSize(Screen.getPrimary().getBounds().getWidth(), 30);

        button.setPadding(new Insets(10, 10, 10, 10));
        button.setGraphic(display);
        this.getChildren().add(button);
    }

    public void setRecipeName(String name) {
        recipeName.setText(name);
    }

    public void setMealType(String mealType) {
        recipeMealType.setText(mealType);
    }

    public void setUUID(String uuid) {
        this.uuid=uuid;
    }
}
