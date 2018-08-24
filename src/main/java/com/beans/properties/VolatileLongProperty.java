package com.beans.properties;

public class VolatileLongProperty extends LongPropertyBase {

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
}
