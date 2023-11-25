package client;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class LoginView extends GridPane {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private CheckBox rememberMeCheckBox;
    private Label errorMessage;
    private Hyperlink createAccountLink;

    public LoginView() {
        // Initialize components and layout properties
        initializeComponents();
        setupLayout();
        // The controller will be set externally after constructing the view
    }

    private void initializeComponents() {
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Login");
        rememberMeCheckBox = new CheckBox("Remember me");
        createAccountLink = new Hyperlink("Don't have an account? Create one");
        errorMessage = new Label();
    }

    private void setupLayout() {
        this.setAlignment(Pos.CENTER);
        this.setHgap(10);
        this.setVgap(10);
        this.setPadding(new Insets(25, 25, 25, 25));

        this.add(new Text("Login"), 0, 0, 2, 1);
        this.add(new Label("Username:"), 0, 1);
        this.add(usernameField, 1, 1);
        this.add(new Label("Password:"), 0, 2);
        this.add(passwordField, 1, 2);
        this.add(rememberMeCheckBox, 1, 3);
        this.add(loginButton, 1, 4);
        this.add(createAccountLink, 1, 5);
        this.add(errorMessage, 1, 6);
    }

    public void setController(LoginController controller) {
        // Set the action events for the buttons with the controller
        loginButton.setOnAction(e -> controller.handleLogin(
                usernameField.getText(),
                passwordField.getText(),
                rememberMeCheckBox.isSelected()
        ));
        createAccountLink.setOnAction(e -> controller.handleCreateAccount());
    }

    public void setErrorMessage(String message) {
        errorMessage.setText(message);
    }

    public TextField getUsernameField() {
        return usernameField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public Button getLoginButton() {
        return loginButton;
    }

    public CheckBox getRememberMeCheckBox() {
        return rememberMeCheckBox;
    }

    public Hyperlink getCreateAccountLink() {
        return createAccountLink;
    }

    public Label getErrorMessage() {
        return errorMessage;
    }
}
