package com.beans.observables.properties.concurrent;

import com.beans.observables.properties.ObservableIntProperty;

public class AtomicObservableIntProperty extends ObservableIntProperty {

    private volatile int mValue;

    public AtomicObservableIntProperty(int initialValue) {
        super(initialValue, true);
        mValue = initialValue;
    }

    public AtomicObservableIntProperty() {
        this(0);
    }

    @Override
    public void setAsInt(int value) {
        synchronized (this) {
            if (mValue != value) {
                mValue = value;
                fireValueChangedEvent(value);
            }
        }
    }

    @Override
    public int getAsInt() {
        return mValue;
    }
}
