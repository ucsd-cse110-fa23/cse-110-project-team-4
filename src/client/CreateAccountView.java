package client;

import javafx.scene.layout.*;
import models.Model;

public class CreateAccountView extends BorderPane {
  private Header header;
  private Footer footer;
  private Model model;
  private CreateAccountBody createAccountBody;

  CreateAccountViewController cavc;

  CreateAccountView(CreateAccountViewController cavc) {

    // Initialise the header Object
    model = new Model();
    header = new Header();
    header.setHeaderText("Create Account");

    createAccountBody = new CreateAccountBody(cavc);

    // Initialise the Footer Object
    footer = new Footer();

    this.setTop(header);
    this.setCenter(createAccountBody);
    this.setBottom(footer);
    this.cavc = cavc;
  }

}
