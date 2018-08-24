package com.beans.observables.properties.concurrent;

import com.beans.observables.properties.ObservableDoubleProperty;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link ObservableDoubleProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsDouble(double)}
 *     and {@link #getAsDouble()}.
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
public class AtomicObservableDoubleProperty extends ObservableDoubleProperty {

    private volatile double mValue;

    public AtomicObservableDoubleProperty(double initialValue) {
        super(true);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public AtomicObservableDoubleProperty() {
        this(0.0);
    }

    @Override
    public void setAsDouble(double value) {
        synchronized (this) {
            if (mValue != value) {
                double oldValue = mValue;
                mValue = value;
                fireValueChangedEvent(oldValue, value);
            }
        }
    }

    @Override
    public double getAsDouble() {
        return mValue;
    }
}
