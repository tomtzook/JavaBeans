package com.beans.observables.binding;

import com.beans.observables.properties.ObservableProperty;

public interface PropertyBindingController<T> extends BindingController<T> {

    void bindBidirectional(ObservableProperty<T> observableProperty);
}
