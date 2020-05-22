package com.beans.observables.properties.atomic;

import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.beans.observables.properties.ObservableIntProperty;
import com.notifier.EventController;

import java.util.concurrent.atomic.AtomicInteger;

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
public class AtomicObservableIntProperty extends ObservableIntProperty {

    private final AtomicInteger mValue;

    public AtomicObservableIntProperty(ObservableEventController<Integer> eventController,
                                       PropertyBindingController<Integer> bindingController,
                                       int initialValue) {
        super(eventController, bindingController);
        mValue = new AtomicInteger(initialValue);
    }

    public AtomicObservableIntProperty(EventController eventController,
                                          PropertyBindingController<Integer> bindingController,
                                          int initialValue) {
        super(eventController, bindingController);
        mValue = new AtomicInteger(initialValue);
    }

    public AtomicObservableIntProperty(ObservableEventController<Integer> eventController,
                                       PropertyBindingController<Integer> bindingController) {
        this(eventController, bindingController,0);
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
