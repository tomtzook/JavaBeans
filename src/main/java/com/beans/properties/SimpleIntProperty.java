package com.beans.properties;

import com.beans.IntProperty;

public class SimpleIntProperty implements IntProperty {

    private int mValue;

    public SimpleIntProperty(int value) {
        mValue = value;
    }

    public SimpleIntProperty() {
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
