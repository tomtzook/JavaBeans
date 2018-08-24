package com.beans.properties;

import com.beans.Property;

/**
 * <p>
 *     Base for {@link Property} implementations.
 * </p>
 *
 * @param <T> type of the property data
 *
 * @since JavaBeans 1.0
 */
public abstract class PropertyBase<T> implements Property<T> {

    @Override
    public String toString() {
        return String.format("Property [value=%s]", String.valueOf(get()));
    }
}
