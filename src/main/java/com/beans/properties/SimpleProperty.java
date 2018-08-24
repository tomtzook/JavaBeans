package com.beans.properties;

import com.beans.Property;

public class SimpleProperty<T> implements Property<T> {

    private T mValue;

    public SimpleProperty(T initialValue) {
        mValue = initialValue;
    }

    public SimpleProperty() {
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
        return mValue.toString();
    }
}
