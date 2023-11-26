package client;

import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DetailedRecipeInfoBody extends VBox {
    private TextField recipeName;
    private TextArea recipeContent;
    private String uuid;
    private Long createdAt;
    
    private boolean isNewRecipe;

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

        this.isNewRecipe = true;

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

    public String getUUID(){
            return this.uuid;
    }

    public boolean getIsNewRecipe() {
        return this.isNewRecipe;
    }

    public Long getCreatedAt() {
        return this.createdAt;
    }

    public void setRecipeNAme(String s){
        this.recipeName.setText(s);
    }

    public void setRecipeContext(String s){
        this.recipeContent.setText(s);
    }

    public void setUUId(String uuid){
        this.uuid = uuid;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public void setIsNewRecipe(boolean isNewRecipe) {
        this.isNewRecipe = isNewRecipe;
    }

    
    
}
