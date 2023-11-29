package client;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class DetailedNewRecipeView extends DetailedRecipeViewTemplate {
    Button reloadButton = new Button("Reload");
    public DetailedNewRecipeView(DetailedViewController vc) {
        super(vc);
        this.addNewListeners();
        ((HBox)super.header.getChildren().get(2)).getChildren().add(reloadButton);
    }

    public void addNewListeners() {
        
        reloadButton.setOnAction(e -> {
            System.out.println("Reload");
        });
    }
    
}
