package com.beans.properties;

import com.beans.LongProperty;

public class VolatileLongProperty implements LongProperty {

    private volatile long mValue;

    public VolatileLongProperty(long value) {
        mValue = value;
    }

    public VolatileLongProperty() {
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
