package com.beans.observables.properties;

import com.beans.IntProperty;
import com.beans.observables.ObservableIntValue;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.notifier.EventController;

import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 *     A <em>int</em> specialization of {@link ObservableProperty}.
 *     Provides methods to access using primitive types: {@link #getAsInt()}, {@link #setAsInt(int)}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     Implements {@link #set(Integer)} and {@link #get()} as proxy calls to {@link #setAsInt(int)}
 *     and {@link #getAsInt()} respectively.
 * </p>
 * <p>
 *     This property is <b>not</b> <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public abstract class ObservableIntProperty extends ObservablePropertyBase<Integer>
        implements ObservableIntValue, IntProperty {

    protected ObservableIntProperty(ObservableEventController<Integer> eventController,
                                    PropertyBindingController<Integer> bindingController) {
        super(eventController, bindingController);
    }

    protected ObservableIntProperty(EventController eventController,
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
