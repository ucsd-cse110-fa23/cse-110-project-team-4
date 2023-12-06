package client.detailedView;

import java.io.ByteArrayInputStream;
import javafx.geometry.Pos;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class DetailedRecipeInfoBody extends VBox {
    private TextField recipeName;
    private TextArea recipeContent;
    private String uuid;
    private long createdAt;
    private TextField mealType;
    private byte[] imageArray;

    private Image image;
    private ImageView imageView;
    
    private boolean isNewRecipe;

    DetailedRecipeInfoBody() {
        
        mealType = new TextField();
        mealType.setText("Breakfast");
        mealType.setPrefSize(40, 40);
        mealType.setAlignment(Pos.CENTER);
        mealType.setEditable(false);
        this.getChildren().add(mealType);


        recipeName = new TextField();
        recipeName.setText("Ramen");
        recipeName.setPrefSize(40, 40);
        recipeName.setAlignment(Pos.CENTER);
        recipeName.setEditable(false);
        this.getChildren().add(recipeName);


        imageView = new ImageView();
        HBox hbox = new HBox();
        hbox.getChildren().add(imageView);
        hbox.setAlignment(Pos.CENTER);
        this.getChildren().add(hbox);

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

    public byte[] getImageArray() {
        return this.imageArray;
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

    public void setMealType(String mealType){
        this.mealType.setText(mealType);
    }

    public String getMealType(){
        return this.mealType.getText();
    }

    public void setImage(byte[] imageArray) {
        try {
            this.imageArray = imageArray;
            ByteArrayInputStream stream = new ByteArrayInputStream(this.imageArray);
            // BufferedImage bi = ImageIO.read(stream);
            // WritableImage wi = new WritableImage(256, 256);
            this.image = new Image(stream);
            
            this.imageView.setImage(this.image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    
    
}
