package com.beans.properties;

import com.beans.BooleanProperty;

public abstract class BooleanPropertyBase implements BooleanProperty {

    @Override
    public void set(Boolean value) {
        if (value == null) {
            // TODO: LOG?
            setAsBoolean(false);
        } else {
            setAsBoolean(value);
        }
    }

    @Override
    public Boolean get() {
        return getAsBoolean();
    }

    @Override
    public String toString() {
        return String.format("BooleanProperty [value=%b]", getAsBoolean());
    }
}
