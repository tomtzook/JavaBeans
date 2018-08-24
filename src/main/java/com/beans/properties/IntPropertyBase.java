package com.beans.properties;

import com.beans.IntProperty;

public abstract class IntPropertyBase implements IntProperty {

    @Override
    public void set(Integer value) {
        if (value == null) {
            // TODO: LOG?
            setAsInt(0);
        } else {
            setAsInt(value);
        }
    }

    @Override
    public Integer get() {
        return getAsInt();
    }

    @Override
    public String toString() {
        return String.format("IntProperty [value=%d]", getAsInt());
    }
}
