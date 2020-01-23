package com.beans.observables.binding;

import com.beans.observables.ObservableValue;

import java.util.Optional;

public interface BindingController<T> {

    boolean isBound();

    void bind(ObservableValue<T> observableValue);
    void unbind();

    Optional<ObservableBinding<T>> getBinding();
}
