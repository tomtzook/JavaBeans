package com.beans.properties;

public class VolatileDoubleProperty extends DoublePropertyBase {

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
