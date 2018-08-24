package com.beans.observables.properties;

public class SimpleObservableIntProperty extends ObservableIntProperty {

    private int mValue;

    public SimpleObservableIntProperty(int initialValue) {
        super(initialValue, false);
        mValue = initialValue;
    }

    public SimpleObservableIntProperty() {
        this(0);
    }

    @Override
    public void setAsInt(int value) {
        if (mValue != value) {
            mValue = value;
            fireValueChangedEvent(value);
        }
    }

    @Override
    public int getAsInt() {
        return mValue;
    }
}
