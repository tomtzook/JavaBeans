package com.beans;

import java.util.function.IntSupplier;

/**
 * <p>
 *     An <em>int</em> specialization of {@link Property}.
 *     Provides methods to access using primitive types: {@link #getAsInt()}, {@link #setAsInt(int)}.
 * </p>
 * <p>
 *     An extension of {@link IntSupplier}.
 * </p>
 * <p>
 *     This property is <b>not</b> <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public interface IntProperty extends IntSupplier, Property<Integer> {

    /**
     * Gets the value of the property.
     *
     * @return the value of the property.
     */
    @Override
    int getAsInt();

    /**
     * Sets the value of the property.
     *
     * @param value value of the property.
     */
    void setAsInt(int value);

    /**
     * {@inheritDoc}
     * <p>
     *     Setting <em>null</em> is not valid, and will set <em>0</em> instead.
     * </p>
     */
    @Override
    void set(Integer value);
}
