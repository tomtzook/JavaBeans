package com.beans;

import java.util.function.LongSupplier;

/**
 * <p>
 *     A <em>long</em> specialization of {@link Property}.
 *     Provides methods to access using primitive types: {@link #getAsLong()}, {@link #setAsLong(long)}.
 * </p>
 * <p>
 *     An extension of {@link LongSupplier}.
 * </p>
 * <p>
 *     This property is <b>not</b> <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public interface LongProperty extends LongSupplier, Property<Long> {

    /**
     * Gets the value of the property.
     *
     * @return the value of the property.
     */
    @Override
    long getAsLong();

    /**
     * Sets the value of the property.
     *
     * @param value value of the property.
     */
    void setAsLong(long value);

    /**
     * {@inheritDoc}
     * <p>
     *     Setting <em>null</em> is not valid, and will set <em>0</em> instead.
     * </p>
     */
    @Override
    void set(Long value);
}
