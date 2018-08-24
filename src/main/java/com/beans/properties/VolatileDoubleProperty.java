package com.beans.properties;

import com.beans.DoubleProperty;

public class VolatileDoubleProperty implements DoubleProperty {

    private volatile double mValue;

    public VolatileDoubleProperty(double value) {
        mValue = value;
    }

    public VolatileDoubleProperty() {
        this(0.0);
    }

    @Override
    public void setAsDouble(double value) {
        mValue = value;
    }

    @Override
    public double getAsDouble() {
        return mValue;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }
}
