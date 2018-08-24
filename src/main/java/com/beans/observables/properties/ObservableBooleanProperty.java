package com.beans.observables.properties;

import com.beans.BooleanProperty;

public abstract class ObservableBooleanProperty extends ObservablePropertyBase<Boolean> implements BooleanProperty {

    protected ObservableBooleanProperty(boolean initialValue, boolean threadSafe) {
        super(initialValue, threadSafe);
    }

    @Override
    public void set(Boolean value) {
        if (value == null) {
            // TODO: LOG?
            setAsBoolean(false);
        } else {
            setAsBoolean(value);
        }
    }

    @Override
    public Boolean get() {
        return getAsBoolean();
    }

    @Override
    public String toString() {
        return String.format("ObservableBooleanProperty [value=%b]", getAsBoolean());
    }
}
