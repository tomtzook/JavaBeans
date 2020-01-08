package com.beans.properties;

import com.beans.DoubleProperty;

import java.util.Objects;

/**
 * <p>
 *     Base for {@link DoubleProperty} implementations. Implements
 *     {@link #set(Double)} and {@link #get()} as proxy calls to {@link #setAsDouble(double)}
 *     and {@link #getAsDouble()} respectively.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public abstract class DoublePropertyBase implements DoubleProperty {

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
        return String.format("DoubleProperty [value=%f]", getAsDouble());
    }
}
