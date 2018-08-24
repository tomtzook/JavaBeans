package com.beans.properties;

public class VolatileBooleanProperty extends BooleanPropertyBase {

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
}
