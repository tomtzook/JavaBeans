package com.beans.observables.properties.atomic;

import com.beans.observables.binding.AtomicPropertyBindingController;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.beans.observables.properties.ObservableLongProperty;
import com.beans.observables.properties.ObservableLongPropertyBase;
import com.notifier.EventController;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

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
 * <p>
 *     Depending on the {@link ObservableEventController} used, it is possible
 *     that changes from multiple threads won't dispatch in the correct order.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class AtomicObservableLongProperty extends ObservableLongPropertyBase {

    private final AtomicLong mValue;

    public AtomicObservableLongProperty(Object bean,
                                        EventController eventController,
                                        long initialValue) {
        super(bean, eventController);
        mValue = new AtomicLong(initialValue);
    }

    public AtomicObservableLongProperty(EventController eventController,
                                        long initialValue) {
        super(eventController);
        mValue = new AtomicLong(initialValue);
    }

    @Override
    protected void setInternalDirect(Long value) {
        mValue.set(value);
    }

    @Override
    protected void setInternal(long value) {
        long oldValue = mValue.getAndSet(value);
        if (oldValue != value) {
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    protected long getInternal() {
        return mValue.get();
    }
}
