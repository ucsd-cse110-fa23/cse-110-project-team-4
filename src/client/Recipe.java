package client;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;

public class Recipe extends HBox {
  Button recipeName;

  Recipe() {
    this.setSpacing(10);
    this.setStyle("-fx-background-color: #DAE5EA; -fx-border-width: 0; -fx-font-weight: bold;"); // sets background
    this.setPrefSize(Screen.getPrimary().getBounds().getWidth(), 30);

    recipeName = new Button();
    recipeName.setStyle("-fx-background-color: #DAE5FF; -fx-border-width: 0;"); // set background color of texfield
    recipeName.setPrefSize(Screen.getPrimary().getBounds().getWidth(), 30);

    recipeName.setPadding(new Insets(10, 10, 10, 10));
    this.getChildren().add(recipeName);

    addListeners();

  }

  public void setRecipeName(String name) {
    recipeName.setText(name);
  }

  public void addListeners() {
    recipeName.setOnAction(e -> {
      System.out.println("HERE");
    });
  }
}
