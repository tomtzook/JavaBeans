package com.beans.properties;

import com.beans.DoubleProperty;

public abstract class DoublePropertyBase implements DoubleProperty {

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
        return String.format("DoubleProperty [value=%f]", getAsDouble());
    }
}
