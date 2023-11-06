package client;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

public class Recipe extends HBox {
  private Button recipeName;

  Recipe() {
    this.setPrefSize(500, 20); // sets size of recipe
    this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background

    recipeName = new Button("Recipe Name");
    recipeName.setPrefSize(Screen.getPrimary().getBounds().getWidth(), 20);
    recipeName.setStyle("-fx-background-color: #DAE5FF; -fx-border-width: 0;");
    recipeName.setPadding(new Insets(10, 10, 10, 10));
    this.getChildren().add(recipeName);

    addListeners();
  }

  public void addListeners() {
    recipeName.setOnAction(e -> {
      // go to detail recipe screen
    });
  }
}
