package com.beans.observables.properties;

public class SimpleObservableDoubleProperty extends ObservableDoubleProperty {

    private double mValue;

    public SimpleObservableDoubleProperty(double initialValue) {
        super(initialValue, false);
        mValue = initialValue;
    }

    public SimpleObservableDoubleProperty() {
        this(0.0);
    }

    @Override
    public void setAsDouble(double value) {
        if (mValue != value) {
            mValue = value;
            fireValueChangedEvent(value);
        }
    }

    @Override
    public double getAsDouble() {
        return mValue;
    }
}
