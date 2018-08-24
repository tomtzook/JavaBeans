package com.beans.properties;

public class VolatileProperty<T> extends PropertyBase<T> {

    private volatile T mValue;

    public VolatileProperty(T initialValue) {
        mValue = initialValue;
    }

    public VolatileProperty() {
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
