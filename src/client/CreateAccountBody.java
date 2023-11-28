package client;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.json.JSONObject;

public class CreateAccountBody extends VBox {
  GridPane grid;
  Text welcomeText;
  Text usernameText;
  Text usernameErrorMessage;
  Text passwordText;
  Text passwordErrorMessage;
  Text alreadyLoggedInText;

  TextField usernameField;
  PasswordField passwordField;
  Button createAccountButton;
  Button loginPageButton;
  HBox loginRedict;

  CreateAccountViewController cavc;

  CreateAccountBody(CreateAccountViewController cavc) {
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

        try {
          String urlString = "http://localhost:8100/user";
          URL url = new URI(urlString).toURL();
          HttpURLConnection conn = (HttpURLConnection) url.openConnection();
          conn.setRequestMethod("POST");
          conn.setDoOutput(true);

          OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
          JSONObject createUserRequest = new JSONObject();
          createUserRequest.put("username", u);
          createUserRequest.put("password", p);
          System.out.println(u + " " + p);
          out.write(createUserRequest.toString());
          out.flush();
          out.close();

          BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          // String response = in.readLine();
          in.close();

        } catch (Exception ex) {
          ex.printStackTrace();
          System.out.println("Error" + ex.getMessage());
        }

      }

      // then transition to login page
    });

    loginPageButton.setOnAction(e -> {
      // Transition to login page using main view controller
      // this.cavc.transitionToLogin
    });
  }

}
