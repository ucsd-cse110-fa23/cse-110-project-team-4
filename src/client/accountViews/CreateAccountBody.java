package client.accountViews;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import models.Model;
import client.ServerErrorNotification;

public class CreateAccountBody extends VBox {
  private Text usernameText;
  private Text usernameErrorMessage;
  private Text passwordText;
  private Text passwordErrorMessage;
  private Text alreadyLoggedInText;
  private Model model;

  private TextField usernameField;
  private PasswordField passwordField;
  private Button createAccountButton;
  private Button loginPageButton;
  private HBox loginRedict;

  CreateAccountViewController cavc;

  CreateAccountBody(CreateAccountViewController cavc) {
    model = new Model();
    this.cavc = cavc;

    usernameText = new Text("Username: ");
    usernameText.setFont(Font.font(20));
    usernameField = new TextField();
    usernameField.setMaxWidth(250);
    usernameErrorMessage = new Text("");
    usernameErrorMessage.setFill(Color.RED);

    this.getChildren().add(usernameText);
    this.getChildren().add(usernameField);
    this.getChildren().add(usernameErrorMessage);

    passwordText = new Text("Password: ");
    passwordText.setFont(Font.font(20));
    passwordField = new PasswordField();
    passwordField.setMaxWidth(250);
    passwordErrorMessage = new Text("");
    passwordErrorMessage.setFill(Color.RED);

    this.getChildren().add(passwordText);
    this.getChildren().add(passwordField);
    this.getChildren().add(passwordErrorMessage);

    createAccountButton = new Button("Create Account");
    this.getChildren().add(createAccountButton);

    loginRedict = new HBox();
    alreadyLoggedInText = new Text("Already have an Account? ");
    loginPageButton = new Button("Login");
    loginRedict.getChildren().add(alreadyLoggedInText);
    loginRedict.getChildren().add(loginPageButton);
    loginRedict.setAlignment(Pos.CENTER);
    this.getChildren().add(loginRedict);

    this.setSpacing(10);
    this.setAlignment(Pos.CENTER);
    addListeners();
  }

  public void addListeners() {
    createAccountButton.setOnAction(e -> {
      // get data
      String u = this.usernameField.getText();
      String p = this.passwordField.getText();

      // check that neither username nor password is emtpy
      // if empty reload, display, error, otherwise make user, redirect
      if (u.equals("") || p.equals("")) {
        if (u.equals("")) {
          this.usernameErrorMessage.setText("Error: Username cannot be empty");
        } else {
          this.usernameErrorMessage.setText("");
        }
        if (p.equals("")) {
          this.passwordErrorMessage.setText("Error: Username cannot be empty");
        } else {
          this.passwordErrorMessage.setText("");
        }
      } else {
        this.usernameErrorMessage.setText("");
        this.passwordErrorMessage.setText("");

      }
      String status = model.createAccount(u, p);
      if (status.equals("Account Created")) {
        this.cavc.transitionToLogin();
      } 
      else if(status.equals("Error Connection refused: connect")){
        ServerErrorNotification.alertNoConn();
        Platform.exit();
      }
      else {
        // show error message here
        showErrorMessage("Could not create account");
        System.out.println("Could not create account");
      }
      System.out.println(status);
    });

    loginPageButton.setOnAction(e -> {
      this.cavc.transitionToLogin();
      this.cavc.transitionToLogin();
    });
  }

  private void showErrorMessage(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Create Account Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
