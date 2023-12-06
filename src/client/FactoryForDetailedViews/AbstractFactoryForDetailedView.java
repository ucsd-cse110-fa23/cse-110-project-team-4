package client.factoryForDetailedViews;

import client.detailedView.DView;


public abstract class AbstractFactoryForDetailedView<T> {
    public abstract DView getView(T type);
}




