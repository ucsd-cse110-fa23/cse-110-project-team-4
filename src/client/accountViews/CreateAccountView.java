package client.accountViews;

import client.Footer;
import client.Header;
import javafx.scene.layout.*;

public class CreateAccountView extends BorderPane {
  private Header header;
  private Footer footer;
  private CreateAccountBody createAccountBody;

  CreateAccountViewController cavc;

  CreateAccountView(CreateAccountViewController cavc) {

    // Initialise the header Object
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
