package client.test;

import org.junit.jupiter.api.Test;

import client.DummyViewController;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DetailedViewWindowTests {
    private DummyViewController dummyViewController;

    @BeforeEach
    void setup(){
        
    }

    @Test
    void checkDisplayAddCloseDisplay(){
        dummyViewController = new DummyViewController();
        dummyViewController.display();
        assertTrue(dummyViewController.screenVisbleStatus);
        dummyViewController.closeDisplay();
        assertFalse(dummyViewController.screenVisbleStatus);
    }

}
