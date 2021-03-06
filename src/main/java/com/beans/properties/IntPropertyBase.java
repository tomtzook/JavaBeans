package com.beans.properties;

import com.beans.IntProperty;

import java.util.Objects;

/**
 * <p>
 *     Base for {@link IntProperty} implementations. Implements
 *     {@link #set(Integer)} and {@link #get()} as proxy calls to {@link #setAsInt(int)}
 *     and {@link #getAsInt()} respectively.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public abstract class IntPropertyBase implements IntProperty {

    @Override
    public void set(Integer value) {
        setAsInt(Objects.requireNonNull(value, "null value"));
    }

    @Override
    public Integer get() {
        return getAsInt();
    }

    @Override
    public String toString() {
        return String.format("IntProperty [value=%d]", getAsInt());
    }
}
