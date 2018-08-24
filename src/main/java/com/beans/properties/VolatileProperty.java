package com.beans.properties;

import com.beans.Property;

public class VolatileProperty<T> implements Property<T> {

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

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }
}
