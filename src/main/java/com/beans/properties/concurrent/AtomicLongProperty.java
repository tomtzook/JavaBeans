package com.beans.properties.concurrent;

import com.beans.properties.LongPropertyBase;

public class AtomicLongProperty extends LongPropertyBase {

    private volatile long mValue;

    public AtomicLongProperty(long value) {
        mValue = value;
    }

    public AtomicLongProperty() {
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
