package com.beans.observables.properties;

public class SimpleObservableLongProperty extends ObservableLongProperty {

    private long mValue;

    public SimpleObservableLongProperty(long initialValue) {
        super(initialValue, false);
        mValue = initialValue;
    }

    public SimpleObservableLongProperty() {
        this(0);
    }

    @Override
    public void setAsLong(long value) {
        if (mValue != value) {
            mValue = value;
            fireValueChangedEvent(value);
        }
    }

    @Override
    public long getAsLong() {
        return mValue;
    }
}
