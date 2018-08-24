package com.beans.observables.properties;

import com.beans.DoubleProperty;

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
    
    public ObservableDoubleProperty(double initialValue, boolean threadSafe) {
        super(initialValue, threadSafe);
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
        if (value == null) {
            // TODO: LOG?
            setAsDouble(0.0);
        } else {
            setAsDouble(value);
        }
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
