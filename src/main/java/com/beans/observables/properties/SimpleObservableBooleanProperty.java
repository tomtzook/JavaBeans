package com.beans.observables.properties;

public class SimpleObservableBooleanProperty extends ObservableBooleanProperty {

    private boolean mValue;

    public SimpleObservableBooleanProperty(boolean initialValue) {
        super(initialValue, false);
    }

    public SimpleObservableBooleanProperty() {
        this(false);
    }

    @Override
    public void setAsBoolean(boolean value) {
        if (mValue != value) {
            mValue = value;
            fireValueChangedEvent(value);
        }
    }

    @Override
    public boolean getAsBoolean() {
        return mValue;
    }
}
