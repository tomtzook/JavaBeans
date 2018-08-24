package com.beans.properties;

import com.beans.BooleanProperty;

public class VolatileBooleanProperty implements BooleanProperty {

    private volatile boolean mValue;

    public VolatileBooleanProperty(boolean value) {
        mValue = value;
    }

    public VolatileBooleanProperty() {
        this(false);
    }

    @Override
    public void setAsBoolean(boolean value) {
        mValue = value;
    }

    @Override
    public boolean getAsBoolean() {
        return mValue;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }
}
