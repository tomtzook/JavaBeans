package com.beans.properties;

public class SimpleDoubleProperty extends DoublePropertyBase {
    
    private double mValue;

    public SimpleDoubleProperty(double value) {
        mValue = value;
    }

    public SimpleDoubleProperty() {
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
