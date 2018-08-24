package com.beans.observables.properties.concurrent;

import com.beans.observables.properties.ObservablePropertyBase;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link com.beans.observables.properties.ObservableProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #set(Object)}
 *     and {@link #get()}.
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
 * @param <T> type of the property data.
 *
 * @since JavaBeans 1.0
 */
public class AtomicObservableProperty<T> extends ObservablePropertyBase<T> {

    private volatile T mValue;

    public AtomicObservableProperty(T initialValue) {
        super(true);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>null</em>.
     */
    public AtomicObservableProperty() {
        this(null);
    }

    @Override
    public void set(T value) {
        synchronized (this) {
            if (mValue != value) {
                T oldValue = mValue;
                mValue = value;
                fireValueChangedEvent(oldValue, value);
            }
        }
    }

    @Override
    public T get() {
        return mValue;
    }
}
