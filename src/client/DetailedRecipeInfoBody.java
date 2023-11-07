package client;

import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DetailedRecipeInfoBody extends VBox {
    private String recipeNameStr;
    private String recipeContentStr;
    private int id;
    private TextField recipeNameTF;
    private TextArea recipeContentTA;

    public DetailedRecipeInfoBody(String recipeNameStr, String recipeContentStr) {
        this.recipeNameStr = recipeNameStr;
        this.recipeContentStr = recipeContentStr;
    }

    public void createUIBody(){
        recipeNameTF = new TextField();
        recipeNameTF.setText("Ramen");
        recipeNameTF.setPrefSize(40, 40);
        recipeNameTF.setAlignment(Pos.CENTER);
        recipeNameTF.setEditable(false);
        this.getChildren().add(recipeNameTF);

        recipeContentTA = new TextArea();
        recipeContentTA.setText("jhewgaojhfaedfjhikgbliausdbfgiuabdslifgblkadfbgilayrsdbgljkhaebdfjhgbajlkhdyfbglkahdbfgiohyavbdfuioyhgbakldjhfbgihadvbfyuihgbsldjkhfgbkajhbfihgbaslekfgbklajebyrhijkbaeioujbhfipuoaedrbyhiojuaeubn");
        recipeContentTA.setWrapText(true);
        VBox.setVgrow(recipeContentTA, Priority.SOMETIMES);
        // recipeName.setAlignment(TextAlignment.CENTER);
        recipeContentTA.setEditable(false);
        this.getChildren().add(recipeContentTA);
    }

    public void textFieldEditable(boolean b) {
        recipeNameTF.setEditable(b);
        recipeContentTA.setEditable(b);
    }

    public String getRecipeName(){
        return recipeNameStr;
    }

    public String getRecipeContent(){
        return recipeContentStr;
    }

    public int getID(){
            return this.id;
    }

    public void setRecipeName(String s){
        this.recipeNameTF.setText(s);
    }

    public void setRecipeContext(String s){
        this.recipeContentTA.setText(s);
    }

    public void setId(int id){
        this.id = id;
    }

    
    
}
