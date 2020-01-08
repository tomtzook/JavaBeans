package com.beans.observables.properties;

import com.beans.DoubleProperty;

import java.util.Objects;

/**
 * <p>
 *     A <em>double</em> specialization of {@link ObservableProperty}.
 *     Provides methods to access using primitive types: {@link #getAsDouble()}, {@link #setAsDouble(double)}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     Implements {@link #set(Double)} and {@link #get()} as proxy calls to {@link #setAsDouble(double)}
 *     and {@link #getAsDouble()} respectively.
 * </p>
 * <p>
 *     This property is <b>not</b> <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public abstract class ObservableDoubleProperty extends ObservablePropertyBase<Double> implements DoubleProperty {
    
    public ObservableDoubleProperty(boolean threadSafe) {
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
    public abstract void setAsDouble(double value);

    @Override
    public void set(Double value) {
        setAsDouble(Objects.requireNonNull(value, "null value"));
    }

    @Override
    public Double get() {
        return getAsDouble();
    }

    @Override
    public String toString() {
        return String.format("ObservableDoubleProperty [value=%f]", getAsDouble());
    }
}
