package com.beans.observables.properties;

import java.util.Objects;

public class SimpleObservableProperty<T> extends ObservablePropertyBase<T> {

    private T mValue;

    public SimpleObservableProperty(T initialValue) {
        super(initialValue, false);
        mValue = initialValue;
    }

    public SimpleObservableProperty() {
        this(null);
    }

    @Override
    public void set(T value) {
        if (!Objects.equals(mValue, value)) {
            mValue = value;
            fireValueChangedEvent(value);
        }
    }

    @Override
    public T get() {
        return mValue;
    }
}
