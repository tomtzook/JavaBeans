package com.beans.properties.concurrent;

import com.beans.properties.BooleanPropertyBase;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link com.beans.BooleanProperty BooleanProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsBoolean(boolean)}
 *     and {@link #getAsBoolean()}.
 * </p>
 * <p>
 *     A boxed version of the access interface for writing and reading is available
 *     using {@link #set(Boolean)} and {@link #get()}, which are basically proxies
 *     for the primitive interface.
 * </p>
 * <p>
 *     This implementation relays on the fact that the Java Language Specifications guarantee that an access (read/write) 
 *     to a <em>volatile</em> is atomic, and will be visible to all threads.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class AtomicBooleanProperty extends BooleanPropertyBase {

    private volatile boolean mValue;

    public AtomicBooleanProperty(boolean value) {
        mValue = value;
    }

    /**
     * Initializes the property with a value of <em>false</em>.
     */
    public AtomicBooleanProperty() {
        this(false);
    }

    @Override
    public void setAsBoolean(boolean value) {
        mValue = value;
    }

    @Override
    public boolean getAsBoolean() {
        return mValue;
    }
}
