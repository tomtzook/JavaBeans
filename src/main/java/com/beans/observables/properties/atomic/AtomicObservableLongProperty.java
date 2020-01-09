package com.beans.observables.properties.atomic;

import com.beans.observables.listeners.ObservableEventController;
import com.beans.observables.properties.ObservableLongProperty;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link ObservableLongProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsLong(long)}
 *     and {@link #getAsLong()}.
 * </p>
 * <p>
 *     This implementation uses the <em>java.util.concurrent.atomic</em> package, to provide
 *     a lock-free, atomic read and write operations.
 * </p>
 * <p>
 *     In some cases, {@link AtomicLong} may old a lock, specifically, if the operating
 *     system does not support 64-bit operations.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class AtomicObservableLongProperty extends ObservableLongProperty {

    private final AtomicLong mValue;

    public AtomicObservableLongProperty(ObservableEventController<Long> eventController, long initialValue) {
        super(eventController);
        mValue = new AtomicLong(initialValue);
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public AtomicObservableLongProperty(ObservableEventController<Long> eventController) {
        this(eventController, 0);
    }

    @Override
    public void setAsLong(long value) {
        long oldValue = mValue.getAndSet(value);
        if (oldValue != value) {
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public long getAsLong() {
        return mValue.get();
    }
}
