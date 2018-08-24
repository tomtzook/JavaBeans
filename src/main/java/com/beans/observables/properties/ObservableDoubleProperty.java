package com.beans.observables.properties;

import com.beans.DoubleProperty;

public abstract class ObservableDoubleProperty extends ObservablePropertyBase<Double> implements DoubleProperty {
    
    public ObservableDoubleProperty(double initialValue, boolean threadSafe) {
        super(initialValue, threadSafe);
    }

    @Override
    public void set(Double value) {
        if (value == null) {
            // TODO: LOG?
            setAsDouble(0.0);
        } else {
            setAsDouble(value);
        }
    }

    @Override
    public Double get() {
        return getAsDouble();
    }

    @Override
    public String toString() {
        return String.format("ObservableDoubleProperty [value=%f]", getAsDouble());
    }
}
