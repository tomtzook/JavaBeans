package com.beans.observables.properties;

import com.beans.IntProperty;

public abstract class ObservableIntProperty extends ObservablePropertyBase<Integer> implements IntProperty {

    protected ObservableIntProperty(int initialValue, boolean threadSafe) {
        super(initialValue, threadSafe);
    }

    @Override
    public void set(Integer value) {
        if (value == null) {
            // TODO: LOG?
            setAsInt(0);
        } else {
            setAsInt(value);
        }
    }

    @Override
    public Integer get() {
        return getAsInt();
    }

    @Override
    public String toString() {
        return String.format("ObservableIntProperty [value=%d]", getAsInt());
    }
}
