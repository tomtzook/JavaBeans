package com.beans;

import java.util.function.Supplier;

/**
 * <p>
 *     A mutable property. Provides access to a value which could be modified by other sources.
 * </p>
 * <p>
 *     An extension of {@link Supplier}, with mutability.
 * </p>
 *
 *
 * @param <T> type of data
 *
 * @since JavaBeans 1.0
 */
public interface Property<T> extends Supplier<T> {

    /**
     * Gets the value of the property.
     *
     * @return the value of the property.
     */
    @Override
    T get();

    /**
     * Sets the value of the property.
     *
     * @param value value to set.
     */
    void set(T value);
}
