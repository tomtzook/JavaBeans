package com.beans.observables.properties.concurrent;

import com.beans.observables.properties.ObservableDoubleProperty;

public class AtomicObservableDoubleProperty extends ObservableDoubleProperty {

    private volatile double mValue;

    public AtomicObservableDoubleProperty(double initialValue) {
        super(initialValue, true);
        mValue = initialValue;
    }

    public AtomicObservableDoubleProperty() {
        this(0.0);
    }

    @Override
    public void setAsDouble(double value) {
        synchronized (this) {
            if (mValue != value) {
                mValue = value;
                fireValueChangedEvent(value);
            }
        }
    }

    @Override
    public double getAsDouble() {
        return mValue;
    }
}
