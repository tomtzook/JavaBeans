package com.beans.observables.properties;

import com.beans.LongProperty;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;

import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 *     A <em>long</em> specialization of {@link ObservableProperty}.
 *     Provides methods to access using primitive types: {@link #getAsLong()}, {@link #setAsLong(long)}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     Implements {@link #set(Long)} and {@link #get()} as proxy calls to {@link #setAsLong(long)}
 *     and {@link #getAsLong()} respectively.
 * </p>
 * <p>
 *     This property is <b>not</b> <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public abstract class ObservableLongProperty extends ObservablePropertyBase<Long> implements LongProperty {

    protected ObservableLongProperty(ObservableEventController<Long> eventController,
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
