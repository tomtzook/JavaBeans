package com.beans.properties.concurrent;

import com.beans.properties.IntPropertyBase;

public class AtomicIntProperty extends IntPropertyBase {

    private volatile int mValue;

    public AtomicIntProperty(int value) {
        mValue = value;
    }

    public AtomicIntProperty() {
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
