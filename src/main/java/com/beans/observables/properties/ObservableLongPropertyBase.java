package com.beans.observables.properties;

import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.notifier.EventController;

import java.util.Objects;
import java.util.Optional;

public abstract class ObservableLongPropertyBase extends ObservablePropertyBase<Long>
        implements ObservableLongProperty {

    protected ObservableLongPropertyBase(ObservableEventController<Long> eventController,
                                     PropertyBindingController<Long> bindingController) {
        super(eventController, bindingController);
    }

    protected ObservableLongPropertyBase(EventController eventController,
                                     PropertyBindingController<Long> bindingController) {
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
    public void setAsLong(long value) {
        if (!setIfBound(value)) {
            setInternal(value);
        }
    }

    @Override
    public long getAsLong() {
        Optional<Long> boundOptional = getIfBound();
        return boundOptional.orElseGet(this::getInternal);
    }

    @Override
    public void set(Long value) {
        Objects.requireNonNull(value, "value is null");

        if (!setIfBound(value)) {
            setInternal(value);
        }
    }

    @Override
    public Long get() {
        Optional<Long> boundOptional = getIfBound();
        return boundOptional.orElseGet(this::getInternal);

    }

    protected abstract void setInternal(long value);
    protected abstract long getInternal();

    @Override
    public String toString() {
        return String.format("ObservableLongProperty [value=%d]", getAsLong());
    }
}
