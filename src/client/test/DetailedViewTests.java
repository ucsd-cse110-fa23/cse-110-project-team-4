package client.test;

import org.junit.jupiter.api.Test;

import client.DetailedRecipeView;
import client.DummyDetailedViewController;
import client.ViewController;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DetailedViewTests {
    @Test
    void sampleTest(){
        ViewController vc = new DummyDetailedViewController();
        DetailedRecipeView d = new DetailedRecipeView(vc);
        assertEquals(4, 4*1);
    }
}
