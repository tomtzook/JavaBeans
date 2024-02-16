package com.beans.observables.binding;

import com.beans.observables.listeners.ChangeEvent;
import com.beans.observables.properties.ObservableProperty;
import com.notifier.RegisteredListener;

import java.util.function.Consumer;

public class BiDirectionalBinding<T> implements ObservableBinding<T> {

    private final ObservableProperty<T> mProperty;
    private final RegisteredListener mListener;

    public BiDirectionalBinding(ObservableProperty<T> property, Consumer<ChangeEvent<T>> onObservableValueChange) {
        mProperty = property;
        mListener = mProperty.addChangeListener(onObservableValueChange::accept);
    }

    @Override
    public void set(T value) {
        mProperty.set(value);
    }

    @Override
    public T get() {
        return mProperty.get();
    }

    @Override
    public void onUnbind() {
        mListener.unregister();
    }
}
