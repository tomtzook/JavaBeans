package com.beans.observables.properties;

import com.beans.BooleanProperty;

import java.util.Objects;

/**
 * <p>
 *     A <em>boolean</em> specialization of {@link ObservableProperty}.
 *     Provides methods to access using primitive types: {@link #getAsBoolean()}, {@link #setAsBoolean(boolean)}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     Implements {@link #set(Boolean)} and {@link #get()} as proxy calls to {@link #setAsBoolean(boolean)}
 *     and {@link #getAsBoolean()} respectively.
 * </p>
 * <p>
 *     This property is <b>not</b> <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public abstract class ObservableBooleanProperty extends ObservablePropertyBase<Boolean> implements BooleanProperty {

    protected ObservableBooleanProperty(boolean threadSafe) {
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
    public abstract void setAsBoolean(boolean value);

    @Override
    public void set(Boolean value) {
        setAsBoolean(Objects.requireNonNull(value, "null value"));
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
