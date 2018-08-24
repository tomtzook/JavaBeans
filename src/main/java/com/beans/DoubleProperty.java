package com.beans;

import java.util.function.DoubleSupplier;

/**
 * <p>
 *     A <em>double</em> specialization of {@link Property}.
 *     Provides methods to access using primitive types: {@link #getAsDouble()}, {@link #setAsDouble(double)}.
 * </p>
 * <p>
 *     An extension of {@link DoubleSupplier}.
 * </p>
 * <p>
 *     This property is <b>not</b> <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public interface DoubleProperty extends DoubleSupplier, Property<Double> {

    /**
     * Gets the value of the property.
     *
     * @return the value of the property.
     */
    @Override
    double getAsDouble();

    /**
     * Sets the value of the property.
     *
     * @param value value of the property.
     */
    void setAsDouble(double value);

    /**
     * {@inheritDoc}
     * <p>
     *     Setting <em>null</em> is not valid, and will set <em>0</em> instead.
     * </p>
     */
    @Override
    void set(Double value);
}
