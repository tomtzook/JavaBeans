package com.beans.observables.properties;

import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.notifier.EventController;

import java.util.Objects;
import java.util.Optional;

public abstract class ObservableIntPropertyBase extends ObservablePropertyBase<Integer>
        implements ObservableIntProperty {

    protected ObservableIntPropertyBase(ObservableEventController<Integer> eventController,
                                    PropertyBindingController<Integer> bindingController) {
        super(eventController, bindingController);
    }

    protected ObservableIntPropertyBase(EventController eventController,
                                    PropertyBindingController<Integer> bindingController) {
        super(eventController, bindingController);
    }
    /**
     * {@inheritDoc}
     * <p>
     *     This call invokes any listeners which are added if it changes
     *     the value which is stored by the property.
     * </p>
     */
    @Override
    public void setAsInt(int value) {
        if (!setIfBound(value)) {
            setInternal(value);
        }
    }

    @Override
    public int getAsInt() {
        Optional<Integer> boundOptional = getIfBound();
        return boundOptional.orElseGet(this::getInternal);
    }

    @Override
    public void set(Integer value) {
        Objects.requireNonNull(value, "value is null");

        if (!setIfBound(value)) {
            setInternal(value);
        }
    }

    @Override
    public Integer get() {
        Optional<Integer> boundOptional = getIfBound();
        return boundOptional.orElseGet(this::getInternal);

    }

    protected abstract void setInternal(int value);
    protected abstract int getInternal();

    @Override
    public String toString() {
        return String.format("ObservableIntProperty [value=%d]", getAsInt());
    }
}