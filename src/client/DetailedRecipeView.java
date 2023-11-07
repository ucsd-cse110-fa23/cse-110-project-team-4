package client;

import javafx.scene.text.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Pos;



public class DetailedRecipeView extends BorderPane{
    private Header header;
    private Footer footer;
    private VBox detailedInfo;
    private Button backButton;
    private Button saveButton;
    private Button editButton;
    private Button deleteButton;
    private TextField recipeName;
    private TextArea recipeContent;
    private ViewController vc;
    public DetailedRecipeView(ViewController vc) {
        this.vc = vc;
    }
    public void makePage(){
        
        // Initialise the header Object
        header = new Header();
        // Initialise the Footer Object
        footer = new Footer();

        this.makeButtons();
        this.addFooterButtons();
        
        this.addHeaderComponents();

        

        // Add header to the top of the BorderPane
        this.setTop(header);
        // Add scroller to the centre of the BorderPane
        // Add footer to the bottom of the BorderPane
        this.setBottom(footer);
        addListeners();
    }
    private void makeButtons(){
        String defaultButtonStyle = "-fx-font-style: italic; -fx-background-color: #FFFFFF;  -fx-font-weight: bold; -fx-font: 11 arial;";
        backButton = new Button("BACK");
        //backButton.setStyle(defaultButtonStyle);
        saveButton = new Button("SAVE");
        //saveButton.setStyle(defaultButtonStyle);
        editButton = new Button("EDIT");
        //editButton.setStyle(defaultButtonStyle);
        deleteButton = new Button("DELETE");
        //deleteButton.setStyle(defaultButtonStyle);
    }

    private void addFooterButtons(){
        footer.getChildren().addAll(deleteButton,editButton,saveButton);
        footer.setAlignment(Pos.CENTER);
    }

    private void addHeaderComponents(){
        HBox lBox = new HBox();
        lBox.getChildren().add(backButton);
        lBox.setAlignment(Pos.CENTER_LEFT);
        lBox.setMaxWidth(180);
        HBox.setHgrow(lBox, Priority.SOMETIMES);
        header.getChildren().add(lBox);
        header.setHeaderText("Detailed View");
        HBox rBox = new HBox();
        rBox.setMaxWidth(180);
        HBox.setHgrow(rBox, Priority.SOMETIMES);

        header.getChildren().add(rBox);
    }

    public void makeBody(String name, String content){
        VBox v = new VBox();
        recipeName = new TextField();
        recipeName.setText(name);
        recipeName.setPrefSize(40, 40);
        recipeName.setAlignment(Pos.CENTER);
        recipeName.setEditable(false);
        this.getChildren().add(recipeName);

        recipeContent = new TextArea();
        recipeContent.setText(content);
        recipeContent.setWrapText(true);
        VBox.setVgrow(recipeContent, Priority.SOMETIMES);
        // recipeName.setAlignment(TextAlignment.CENTER);
        recipeContent.setEditable(false);
        this.getChildren().add(recipeContent);

        this.setCenter(v);
        detailedInfo = v;
    }

    public void addListeners() {
        backButton.setOnAction(e -> {
            this.vc.closeDisplay();
        });

        editButton.setOnAction(e -> {
            recipeName.setEditable(true);
        recipeContent.setEditable(true);
        });
    }
}