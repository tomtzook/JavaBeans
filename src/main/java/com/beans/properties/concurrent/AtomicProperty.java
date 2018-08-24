package com.beans.properties.concurrent;

import com.beans.properties.PropertyBase;

public class AtomicProperty<T> extends PropertyBase<T> {

    private volatile T mValue;

    public AtomicProperty(T initialValue) {
        mValue = initialValue;
    }

    public AtomicProperty() {
        this(null);
    }

    @Override
    public void set(T value) {
        mValue = value;
    }

    @Override
    public T get() {
        return mValue;
    }
}
