package com.beans.observables.properties.atomic;

import com.beans.observables.binding.AtomicPropertyBindingController;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.beans.observables.properties.ObservableIntProperty;
import com.beans.observables.properties.ObservableIntPropertyBase;
import com.notifier.EventController;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link ObservableIntProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsInt(int)}
 *     and {@link #getAsInt()}.
 * </p>
 * <p>
 *     This implementation uses the <em>java.util.concurrent.atomic</em> package, to provide
 *     a lock-free, atomic read and write operations.
 * </p>
 * <p>
 *     Depending on the {@link ObservableEventController} used, it is possible
 *     that changes from multiple threads won't dispatch in the correct order.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class AtomicObservableIntProperty extends ObservableIntPropertyBase {

    private final AtomicInteger mValue;


    public AtomicObservableIntProperty(Object bean,
                                       EventController eventController,
                                       int initialValue) {
        super(bean, eventController);
        mValue = new AtomicInteger(initialValue);
    }

    public AtomicObservableIntProperty(EventController eventController,
                                       int initialValue) {
        super(eventController);
        mValue = new AtomicInteger(initialValue);
    }

    @Override
    protected void setInternalDirect(Integer value) {
        mValue.set(value);
    }

    @Override
    protected void setInternal(int value) {
        int oldValue = mValue.getAndSet(value);
        if (oldValue != value) {
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    protected int getInternal() {
        return mValue.get();
    }
}
