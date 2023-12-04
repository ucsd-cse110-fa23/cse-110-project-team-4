package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import models.Model;

public class LoginBody extends VBox {
    Text usernameText;
    Text passwordText;
    TextField usernameField;
    PasswordField passwordField;
    Text usernameErrorMessage;
    Text passwordErrorMessage;
    CheckBox rememberMeCheckBox;
    Button loginButton;
    Text createAccountPrompt;
    Button createAccountButton;
    HBox createAccountRedirect;
    Model model;
    boolean automaticLoginFlag;
    String userId;

    LoginViewController lvc;

    public LoginBody(LoginViewController lvc) {
        this.lvc = lvc;

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

        rememberMeCheckBox = new CheckBox("Remember me");
        this.getChildren().add(rememberMeCheckBox);

        loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            if (this.rememberMeCheckBox.isSelected()) {
                createAutomaticLoginToken();
            }
            performLogin();
        });
        this.getChildren().add(loginButton);

        createAccountPrompt = new Text("Don't have an account? ");
        createAccountPrompt.setFont(Font.font(16));
        createAccountButton = new Button("Create one.");
        createAccountButton.setOnAction(e -> {
            this.lvc.transitionToCreateAccount();
        });
        ;

        createAccountRedirect = new HBox(10, createAccountPrompt, createAccountButton);
        createAccountRedirect.setAlignment(Pos.CENTER);
        this.getChildren().add(createAccountRedirect);

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);

        model = new Model();
        userId = "";
        performAutomaticLogin();

    }

    private void performLogin() {
        // Retrieve the username and password entered by the user
        String username = usernameField.getText();
        String password = passwordField.getText();

        // You can add validation here to ensure that username and password are not
        // empty
        if (username.isEmpty() || password.isEmpty()) {
            showErrorMessage("Username and password are required.");
            return;
        }

        // You can make an HTTP request to your backend server for authentication
        String response = model.login(username, password);

        // Check the response from the server
        if (response.startsWith("Error")) {
            showErrorMessage("An error occurred during login.");
        } else if (response.equals("Invalid Login Credentials")) {
            showErrorMessage("Invalid username or password.");
        } else {
            // Login successful, you can handle the successful login here
            showInfoMessage("Login successful. Welcome, " + username + "!");
            lvc.login(response);
            lvc.transitionToMultiple();
        }
    }

    private void performAutomaticLogin() {
        FileReader fr;
        try {
            fr = new FileReader("src\\client\\AutomaticLoginToken\\AutomaticLoginToken.txt");
            BufferedReader br = new BufferedReader(fr);
            String username = br.readLine();
            String password = br.readLine();
            String automaticLogin = br.readLine();
            br.close();
            if (automaticLogin.equals("yes")) {
                String response = model.login(username, password);
                if (!(response.startsWith("Error") || response.equals("Invalid Login Credentials"))) {
                    this.automaticLoginFlag = true;
                    this.userId = response.replace("\n", "");
                    // System.out.println(this.userId);
                }
            } else {
                this.automaticLoginFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void createAutomaticLoginToken() {
        try {
            FileWriter fw = new FileWriter("src\\client\\AutomaticLoginToken\\AutomaticLoginToken.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            String username = usernameField.getText();
            String password = passwordField.getText();
            bw.write(username);
            bw.newLine();
            bw.write(password);
            bw.newLine();
            bw.write("yes");
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showErrorMessage(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoMessage(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Login Successful");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public boolean getAutomaticLoginFlag() {
        return this.automaticLoginFlag;
    }

    public String getUserId() {
        return this.userId;
    }

}
