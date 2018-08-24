package com.beans.properties;

import com.beans.LongProperty;

/**
 * <p>
 *     Base for {@link LongProperty} implementations. Implements
 *     {@link #set(Long)} and {@link #get()} as proxy calls to {@link #setAsLong(long)}
 *     and {@link #getAsLong()} respectively.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public abstract class LongPropertyBase implements LongProperty {

    @Override
    public void set(Long value) {
        if (value == null) {
            // TODO: LOG?
            setAsLong(0);
        } else {
            setAsLong(value);
        }
    }

    @Override
    public Long get() {
        return getAsLong();
    }

    @Override
    public String toString() {
        return String.format("LongProperty [value=%d]", getAsLong());
    }
}
