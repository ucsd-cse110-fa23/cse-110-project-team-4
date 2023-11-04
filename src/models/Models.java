package models;

import java.util.Date;
import java.util.UUID;

public class RecipeView {
    String name;
    String details;
}

public class RecipeModel {

    String uuid;
    String name;
    String details;
    long createdOn;

    public Recipe(String name, String details) {
        this.uuid = UUID.randomUUID().toString();
        this.name = n;
        this.details = d;
        this.createdOn = System.currentTimeMillis();
    }

}