package com.beans.observables.binding;

import com.beans.observables.properties.ObservableProperty;

public class BiDirectionalBinding<T> implements ObservableBinding<T> {

    private final ObservableProperty<T> mProperty;

    public BiDirectionalBinding(ObservableProperty<T> property) {
        mProperty = property;
    }

    @Override
    public void set(T value) {
        mProperty.set(value);
    }

    @Override
    public T get() {
        return mProperty.get();
    }
}
