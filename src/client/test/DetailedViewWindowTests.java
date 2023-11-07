package client.test;

import org.junit.jupiter.api.Test;

import client.DetailedRecipeInfoBody;
import client.DummyViewController;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DetailedViewWindowTests {
    private DummyViewController dummyViewController;
    private DetailedRecipeInfoBody drib;

    @BeforeEach
    void setup(){
        
    }

    @Test
    void storingDataInTheUI(){
        //dummyViewController = new DummyViewController();
        //dummyViewController.display();
        //assertTrue(dummyViewController.screenVisbleStatus);
        drib = new DetailedRecipeInfoBody("Ramen","Steps");


    }

}
