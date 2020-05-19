package com.beans.observables.binding;

import com.beans.observables.listeners.ChangeEvent;
import com.beans.observables.properties.ObservableProperty;

import java.util.function.Consumer;

public interface PropertyBindingController<T> extends BindingController<T> {

    void bindBidirectional(ObservableProperty<T> observableProperty, Consumer<ChangeEvent<T>> onObservableValueChange);
}
