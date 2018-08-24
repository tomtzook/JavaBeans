package com.beans.properties;

import com.beans.LongProperty;

public abstract class LongPropertyBase implements LongProperty {

    @Override
    public void set(Long value) {
        if (value == null) {
            // TODO: LOG?
            setAsLong(0);
        } else {
            setAsLong(value);
        }
    }

    @Override
    public Long get() {
        return getAsLong();
    }

    @Override
    public String toString() {
        return String.format("LongProperty [value=%d]", getAsLong());
    }
}
