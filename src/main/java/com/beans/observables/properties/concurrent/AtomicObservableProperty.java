package com.beans.observables.properties.concurrent;

import com.beans.observables.properties.ObservablePropertyBase;

public class AtomicObservableProperty<T> extends ObservablePropertyBase<T> {

    private volatile T mValue;

    public AtomicObservableProperty(T initialValue) {
        super(initialValue, true);
        mValue = initialValue;
    }

    public AtomicObservableProperty() {
        this(null);
    }

    @Override
    public void set(T value) {
        synchronized (this) {
            if (mValue != value) {
                mValue = value;
                fireValueChangedEvent(value);
            }
        }
    }

    @Override
    public T get() {
        return mValue;
    }
}
