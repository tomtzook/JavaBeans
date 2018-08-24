package com.beans.properties;

public class SimpleProperty<T> extends PropertyBase<T> {

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
}
