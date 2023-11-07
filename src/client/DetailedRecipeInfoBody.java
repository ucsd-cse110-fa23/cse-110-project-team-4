package client;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class DetailedRecipeInfoBody extends VBox {
    private TextField recipeName;
    private TextArea recipeContent;
    private int id;

    DetailedRecipeInfoBody() {

        recipeName = new TextField();
        recipeName.setText("Ramen");
        recipeName.setPrefSize(40, 40);
        recipeName.setAlignment(Pos.CENTER);
        recipeName.setEditable(false);
        this.getChildren().add(recipeName);

        recipeContent = new TextArea();
        recipeContent.setText("Ramenjhewgaojhfaedfjhikgbliausdbfgiuabdslifgblkadfbgilayrsdbgljkhaebdfjhgbajlkhdyfbglkahdbfgiohyavbdfuioyhgbakldjhfbgihadvbfyuihgbsldjkhfgbkajhbfihgbaslekfgbklajebyrhijkbaeioujbhfipuoaedrbyhiojuaeubn");
        recipeContent.setWrapText(true);
        VBox.setVgrow(recipeContent, Priority.SOMETIMES);
        // recipeName.setAlignment(TextAlignment.CENTER);
        recipeContent.setEditable(false);
        this.getChildren().add(recipeContent);

    }

    DetailedRecipeInfoBody(String name, String content) {

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

    }

    public void textFieldEditable(boolean b) {
        recipeName.setEditable(b);
        recipeContent.setEditable(b);
    }

    public String getRecipeName(){
        return recipeName.getText();
    }

    public String getRecipeContent(){
        return recipeContent.getText();
    }

    public int getID(){
            return this.id;
    }

    public void setRecipeNAme(String s){
        this.recipeName.setText(s);
    }

    public void setRecipeContext(String s){
        this.recipeContent.setText(s);
    }

    public void setId(int id){
        this.id = id;
    }
}
