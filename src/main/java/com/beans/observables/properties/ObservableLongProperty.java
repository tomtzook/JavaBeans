package com.beans.observables.properties;

import com.beans.LongProperty;

public abstract class ObservableLongProperty extends ObservablePropertyBase<Long> implements LongProperty {

    protected ObservableLongProperty(long initialValue, boolean threadSafe) {
        super(initialValue, threadSafe);
    }

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
        return String.format("ObservableLongProperty [value=%d]", getAsLong());
    }
}
