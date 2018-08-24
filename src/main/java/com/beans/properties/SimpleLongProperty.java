package com.beans.properties;

import com.beans.LongProperty;

public class SimpleLongProperty implements LongProperty {

    private long mValue;

    public SimpleLongProperty(long value) {
        mValue = value;
    }

    public SimpleLongProperty() {
        this(0);
    }

    @Override
    public void setAsLong(long value) {
        mValue = value;
    }

    @Override
    public long getAsLong() {
        return mValue;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }
}
