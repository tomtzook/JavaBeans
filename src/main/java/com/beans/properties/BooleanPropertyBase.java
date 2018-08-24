package com.beans.properties;

import com.beans.BooleanProperty;

/**
 * <p>
 *     Base for {@link BooleanProperty} implementations. Implements
 *     {@link #set(Boolean)} and {@link #get()} as proxy calls to {@link #setAsBoolean(boolean)}
 *     and {@link #getAsBoolean()} respectively.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public abstract class BooleanPropertyBase implements BooleanProperty {

    @Override
    public void set(Boolean value) {
        if (value == null) {
            // TODO: LOG?
            setAsBoolean(false);
        } else {
            setAsBoolean(value);
        }
    }

    @Override
    public Boolean get() {
        return getAsBoolean();
    }

    @Override
    public String toString() {
        return String.format("BooleanProperty [value=%b]", getAsBoolean());
    }
}
