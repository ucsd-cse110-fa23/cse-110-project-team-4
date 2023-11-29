package client.FactoryForDetailedViews;

import client.DView;
import client.DetailedNewRecipeView;
import client.DetailedViewController;

public class FDRV2 extends AbstractFactoryForDetailedView<DetailedViewController> {
    @Override
    public DView getView(DetailedViewController dvc) {
        return new DetailedNewRecipeView(dvc);
    }  
}
