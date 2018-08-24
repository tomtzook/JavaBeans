package com.beans.properties;

public class SimpleLongProperty extends LongPropertyBase {

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
}
