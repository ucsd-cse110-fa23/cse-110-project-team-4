package models;

import java.util.Date;
import java.util.UUID;

public class Recipe {

    public String uuid;
    public String name;
    public String details;
    public long createdOn;

    public Recipe(String name, String details) {
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.details = details;
        this.createdOn = System.currentTimeMillis();
    }

}