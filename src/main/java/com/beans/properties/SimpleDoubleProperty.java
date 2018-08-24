package com.beans.properties;

import com.beans.DoubleProperty;

public class SimpleDoubleProperty implements DoubleProperty {
    
    private double mValue;

    public SimpleDoubleProperty(double value) {
        mValue = value;
    }

    public SimpleDoubleProperty() {
        this(0);
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
