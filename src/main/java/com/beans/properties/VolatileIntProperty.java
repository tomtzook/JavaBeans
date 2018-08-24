package com.beans.properties;

public class VolatileIntProperty extends IntPropertyBase {

    private volatile int mValue;

    public VolatileIntProperty(int value) {
        mValue = value;
    }

    public VolatileIntProperty() {
        this(0);
    }

    @Override
    public void setAsInt(int value) {
        mValue = value;
    }

    @Override
    public int getAsInt() {
        return mValue;
    }
}
