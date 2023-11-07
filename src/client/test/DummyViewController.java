package client.test;

import client.DetailedRecipeView;
import client.ViewController;

public class DummyViewController implements ViewController {
    Boolean screenVisbleStatus;
    
    DummyViewController(){
        ;
    }

    public void display() {
        DetailedRecipeView drv = new DetailedRecipeView(this);
        screenVisbleStatus = true;
    }

    
    public void closeDisplay() {
        screenVisbleStatus = false;
    }
    
}
