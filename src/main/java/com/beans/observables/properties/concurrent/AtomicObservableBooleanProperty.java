package com.beans.observables.properties.concurrent;

import com.beans.observables.properties.ObservableBooleanProperty;

public class AtomicObservableBooleanProperty extends ObservableBooleanProperty {

    private volatile boolean mValue;

    public AtomicObservableBooleanProperty(boolean initialValue) {
        super(initialValue, true);
        mValue = initialValue;
    }

    public AtomicObservableBooleanProperty() {
        this(false);
    }

    @Override
    public void setAsBoolean(boolean value) {
        synchronized (this) {
            if (mValue != value) {
                mValue = value;
                fireValueChangedEvent(value);
            }
        }
    }

    @Override
    public boolean getAsBoolean() {
        return mValue;
    }
}
