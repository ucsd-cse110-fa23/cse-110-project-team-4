package client.factoryForDetailedViews;

import client.detailedView.DView;
import client.detailedView.DetailedOldRecipeView;
import client.detailedView.DetailedViewController;

public class FDRV1 extends AbstractFactoryForDetailedView<DetailedViewController> {
    @Override
    public DView getView(DetailedViewController dvc) {
        return new DetailedOldRecipeView(dvc);
    }  
}
