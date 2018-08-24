package com.beans.properties.concurrent;

import com.beans.properties.PropertyBase;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link com.beans.Property Property}, holding a
 *     variable which is accessed for writing or reading through {@link #set(Object)}
 *     and {@link #get()}.
 * </p>
 * <p>
 *     This implementation relays on the fact that the Java Language Specifications guarantee that an access (read/write)
 *     to a <em>volatile</em> is atomic, and will be visible to all threads.
 * </p>
 *
 * @param <T> type of the property data.
 *
 * @since JavaBeans 1.0
 */
public class AtomicProperty<T> extends PropertyBase<T> {

    private volatile T mValue;

    public AtomicProperty(T initialValue) {
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>null</em>.
     */
    public AtomicProperty() {
        this(null);
    }

    @Override
    public void set(T value) {
        mValue = value;
    }

    @Override
    public T get() {
        return mValue;
    }
}
