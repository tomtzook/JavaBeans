package com.beans.observables.properties;

import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.notifier.EventController;

import java.util.Objects;
import java.util.Optional;

public abstract class ObservableBooleanPropertyBase extends ObservablePropertyBase<Boolean>
        implements ObservableBooleanProperty {

    protected ObservableBooleanPropertyBase(ObservableEventController<Boolean> eventController,
                                        PropertyBindingController<Boolean> bindingController) {
        super(eventController, bindingController);
    }

    protected ObservableBooleanPropertyBase(EventController eventController,
                                        PropertyBindingController<Boolean> bindingController) {
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
    public void setAsBoolean(boolean value) {
        if (!setIfBound(value)) {
            setInternal(value);
        }
    }

    @Override
    public boolean getAsBoolean() {
        Optional<Boolean> boundOptional = getIfBound();
        return boundOptional.orElseGet(this::getInternal);
    }

    @Override
    public void set(Boolean value) {
        Objects.requireNonNull(value, "value is null");

        if (!setIfBound(value)) {
            setInternal(value);
        }
    }

    @Override
    public Boolean get() {
        Optional<Boolean> boundOptional = getIfBound();
        return boundOptional.orElseGet(this::getInternal);

    }

    protected abstract void setInternal(boolean value);
    protected abstract boolean getInternal();

    @Override
    public String toString() {
        return String.format("ObservableBooleanProperty [value=%b]", getAsBoolean());
    }
}
