package com.beans.properties.concurrent;

import com.beans.properties.LongPropertyBase;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link com.beans.LongProperty LongProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsLong(long)}
 *     and {@link #getAsLong()}.
 * </p>
 * <p>
 *     A boxed version of the access interface for writing and reading is available
 *     using {@link #set(Long)} and {@link #get()}, which are basically proxies
 *     for the primitive interface.
 * </p>
 * <p>
 *     This implementation relays on the fact that the Java Language Specifications guarantee that an access (read/write)
 *     to a <em>volatile</em> is atomic, and will be visible to all threads.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class AtomicLongProperty extends LongPropertyBase {

    private volatile long mValue;

    public AtomicLongProperty(long value) {
        mValue = value;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public AtomicLongProperty() {
        this(0);
    }

    @Override
    public void setAsLong(long value) {
        mValue = value;
    }

    @Override
    public long getAsLong() {
        return mValue;
    }
}
