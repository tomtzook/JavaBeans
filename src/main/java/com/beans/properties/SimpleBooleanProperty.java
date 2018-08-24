package com.beans.properties;

public class SimpleBooleanProperty extends BooleanPropertyBase {

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
}
