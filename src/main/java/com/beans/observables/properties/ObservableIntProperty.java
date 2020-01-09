package com.beans.observables.properties;

import com.beans.IntProperty;
import com.beans.observables.listeners.ObservableEventController;

import java.util.Objects;

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
public abstract class ObservableIntProperty extends ObservablePropertyBase<Integer> implements IntProperty {

    protected ObservableIntProperty(ObservableEventController<Integer> eventController) {
        super(eventController);
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This call invokes any listeners which are added if it changes
     *     the value which is stored by the property.
     * </p>
     */
    @Override
    public abstract void setAsInt(int value);

    @Override
    public void set(Integer value) {
        setAsInt(Objects.requireNonNull(value, "value is null"));
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
