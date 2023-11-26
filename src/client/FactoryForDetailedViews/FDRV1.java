package client.FactoryForDetailedViews;

import client.DView;
import client.DetailedRecipeViewTemplate;
import client.DetailedViewController;

public class FDRV1 extends AbstractFactoryForDetailedView<DetailedViewController> {
    @Override
    public DView getView(DetailedViewController dvc) {
        return new DetailedRecipeViewTemplate(dvc);
    }  
}
