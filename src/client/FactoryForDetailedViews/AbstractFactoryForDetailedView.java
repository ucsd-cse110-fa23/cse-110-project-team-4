package client.FactoryForDetailedViews;

import client.DView;


public abstract class AbstractFactoryForDetailedView<T> {
    public abstract DView getView(T type);
}




