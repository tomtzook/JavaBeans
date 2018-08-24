package com.beans;

import java.util.function.BooleanSupplier;

/**
 * <p>
 *     A <em>boolean</em> specialization of {@link Property}.
 *     Provides methods to access using primitive types: {@link #getAsBoolean()}, {@link #setAsBoolean(int)}.
 * </p>
 * <p>
 *     An extension of {@link BooleanSupplier}.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public interface BooleanProperty extends BooleanSupplier, Property<Boolean> {

    /**
     * Gets the value of the property.
     *
     * @return the value of the property.
     */
    @Override
    boolean getAsBoolean();

    /**
     * Sets the value of the property.
     *
     * @param value value of the property.
     */
    void setAsBoolean(boolean value);

    /**
     * {@inheritDoc}
     * <p>
     *     Setting <em>null</em> is not valid, and will set <em>false</em> instead.
     * </p>
     */
    @Override
    void set(Boolean value);
}
