package com.beans.properties;

public class SimpleIntProperty extends IntPropertyBase {

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
}
