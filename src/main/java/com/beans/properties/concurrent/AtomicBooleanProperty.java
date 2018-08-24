package com.beans.properties.concurrent;

import com.beans.properties.BooleanPropertyBase;

public class AtomicBooleanProperty extends BooleanPropertyBase {

    private volatile boolean mValue;

    public AtomicBooleanProperty(boolean value) {
        mValue = value;
    }

    public AtomicBooleanProperty() {
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
