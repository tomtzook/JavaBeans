package com.beans.properties;

import com.beans.IntProperty;

public class VolatileIntProperty implements IntProperty {

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

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }
}
