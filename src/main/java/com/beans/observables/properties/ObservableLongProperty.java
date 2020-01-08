package com.beans.observables.properties;

import com.beans.LongProperty;

import java.util.Objects;

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

    protected ObservableLongProperty(boolean threadSafe) {
        super(threadSafe);
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This call invokes any listeners which are added if it changes
     *     the value which is stored by the property.
     * </p>
     */
    @Override
    public abstract void setAsLong(long value);

    @Override
    public void set(Long value) {
        setAsLong(Objects.requireNonNull(value, "value is null"));
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
