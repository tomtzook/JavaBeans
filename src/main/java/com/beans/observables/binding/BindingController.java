package com.beans.observables.binding;

import com.beans.observables.ObservableValue;
import com.beans.observables.listeners.ChangeEvent;

import java.util.Optional;
import java.util.function.Consumer;

public interface BindingController<T> {

    boolean isBound();

    void bind(ObservableValue<T> observableValue, Consumer<ChangeEvent<T>> onObservableValueChange);
    Optional<ObservableBinding<T>> unbind();

    Optional<ObservableBinding<T>> getBinding();
}
