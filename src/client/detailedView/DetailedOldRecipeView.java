package client.detailedView;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;

public class DetailedOldRecipeView extends DetailedRecipeViewTemplate{

    Button shareButton = new Button("Share");
    public DetailedOldRecipeView(DetailedViewController vc) {
        super(vc);
        this.addNewListeners();
        ((HBox)super.header.getChildren().get(2)).getChildren().add(shareButton);
    }

    public void addNewListeners() {
         
        shareButton.setOnAction(e -> {
            creatURLDisplay();
        });
    }

    public TextInputDialog creatURLDisplay(){
        TextInputDialog dialog = new TextInputDialog();
        //Setting the title
        dialog.setTitle("Dialog");
        ButtonType type = new ButtonType("Ok", ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setGraphic(null);
        dialog.setHeaderText("URL");
        dialog.getEditor().setEditable(false);
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().clear();
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.getEditor().setText(RecipeShareLogic.createShareLinkString(super.getDetailedRecipeInfoBody().getUUID()));
        dialog.showAndWait();
        return dialog;
    }

    
    
}
