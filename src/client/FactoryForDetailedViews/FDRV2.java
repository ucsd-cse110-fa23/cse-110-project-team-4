package client.FactoryForDetailedViews;

import client.detailedView.DView;
import client.detailedView.DetailedNewRecipeView;
import client.detailedView.DetailedViewController;

public class FDRV2 extends AbstractFactoryForDetailedView<DetailedViewController> {
    @Override
    public DView getView(DetailedViewController dvc) {
        return new DetailedNewRecipeView(dvc);
    }  
}
