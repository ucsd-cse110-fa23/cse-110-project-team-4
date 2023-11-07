package client;

public class DummyViewController implements ViewController {
    public Boolean screenVisbleStatus;
    
    public DummyViewController(){
        ;
    }

    public void display() {
        //DetailedRecipeView drv = new DetailedRecipeView(this);
        screenVisbleStatus = true;
    }

    
    public void closeDisplay() {
        screenVisbleStatus = false;
    }
    
}
