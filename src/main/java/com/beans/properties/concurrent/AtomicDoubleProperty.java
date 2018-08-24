package com.beans.properties.concurrent;

import com.beans.properties.DoublePropertyBase;

public class AtomicDoubleProperty extends DoublePropertyBase {

    private volatile double mValue;

    public AtomicDoubleProperty(double value) {
        mValue = value;
    }

    public AtomicDoubleProperty() {
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
