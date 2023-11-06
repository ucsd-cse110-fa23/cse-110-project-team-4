package models;

import java.util.*;

public class Recipe {
    public String uuid;
    public String name;
    public String details;
    public long createdAt;

    public Recipe(String name, String details){
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.details = details;
        this.createdAt = System.nanoTime();
    }

    public Recipe(String recipe){
        String[] recipeStrings = recipe.split(",", 4);
        this.uuid = recipeStrings[0];
        this.name = recipeStrings[1];
        this.details = recipeStrings[2];
        this.createdAt = Long.parseLong(recipeStrings[3]);
    }

    public String toString(){
        return String.format("%s,%s,%s,%d,",uuid, name, details, createdAt);
    }

}
