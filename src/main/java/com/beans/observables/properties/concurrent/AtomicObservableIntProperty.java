package com.beans.observables.properties.concurrent;

import com.beans.observables.properties.ObservableIntProperty;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link ObservableIntProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsInt(int)}
 *     and {@link #getAsInt()}.
 * </p>
 * <p>
 *     This implementation relays on the fact that the Java Language Specifications guarantee that an access (read/write)
 *     to a <em>volatile</em> field is atomic, and will be visible to all threads. Using this, reading
 *     is guaranteed as atomic.
 * </p>
 * <p>
 *     Writing to this property holds a lock, preventing additional write. Invocation
 *     of added listener is done within the lock. This locking doesn't prevent reading
 *     of the updated value.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class AtomicObservableIntProperty extends ObservableIntProperty {

    private volatile int mValue;

    public AtomicObservableIntProperty(int initialValue) {
        super(initialValue, true);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public AtomicObservableIntProperty() {
        this(0);
    }

    @Override
    public void setAsInt(int value) {
        synchronized (this) {
            if (mValue != value) {
                mValue = value;
                fireValueChangedEvent(value);
            }
        }
    }

    @Override
    public int getAsInt() {
        return mValue;
    }
}
