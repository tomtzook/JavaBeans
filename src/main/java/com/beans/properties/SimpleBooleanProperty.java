package com.beans.properties;

import com.beans.BooleanProperty;

public class SimpleBooleanProperty implements BooleanProperty {

    private boolean mValue;

    public SimpleBooleanProperty(boolean value) {
        mValue = value;
    }

    public SimpleBooleanProperty() {
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
