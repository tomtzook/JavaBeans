package com.beans.properties.concurrent;

import com.beans.properties.IntPropertyBase;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link com.beans.IntProperty IntProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsInt(int)}
 *     and {@link #getAsInt()}.
 * </p>
 * <p>
 *     A boxed version of the access interface for writing and reading is available
 *     using {@link #set(Integer)} and {@link #get()}, which are basically proxies
 *     for the primitive interface.
 * </p>
 * <p>
 *     This implementation relays on the fact that the Java Language Specifications guarantee that an access (read/write) 
 *     to a <em>volatile</em> field is atomic, and will be visible to all threads.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class AtomicIntProperty extends IntPropertyBase {

    private volatile int mValue;

    public AtomicIntProperty(int value) {
        mValue = value;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public AtomicIntProperty() {
        this(0);
    }

    @Override
    public void setAsInt(int value) {
        mValue = value;
    }

    @Override
    public int getAsInt() {
        return mValue;
    }
}
