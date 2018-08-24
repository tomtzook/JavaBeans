package com.beans.observables.properties.concurrent;

import com.beans.observables.properties.ObservableLongProperty;

public class AtomicObservableLongProperty extends ObservableLongProperty {

    private volatile long mValue;

    public AtomicObservableLongProperty(long initialValue) {
        super(initialValue, true);
        mValue = initialValue;
    }

    public AtomicObservableLongProperty() {
        this(0);
    }

    @Override
    public void setAsLong(long value) {
        synchronized (this) {
            if (mValue != value) {
                mValue = value;
                fireValueChangedEvent(value);
            }
        }
    }

    @Override
    public long getAsLong() {
        return mValue;
    }
}
