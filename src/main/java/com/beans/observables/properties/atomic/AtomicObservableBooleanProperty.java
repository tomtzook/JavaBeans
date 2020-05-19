package com.beans.observables.properties.atomic;

import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.beans.observables.properties.ObservableBooleanProperty;
import com.notifier.EventController;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link ObservableBooleanProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsBoolean(boolean)}
 *     and {@link #getAsBoolean()}.
 * </p>
 * <p>
 *     This implementation uses the <em>java.util.concurrent.atomic</em> package, to provide
 *     a lock-free, atomic read and write operations.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class AtomicObservableBooleanProperty extends ObservableBooleanProperty {

    private final AtomicBoolean mValue;

    public AtomicObservableBooleanProperty(ObservableEventController<Boolean> eventController,
                                           PropertyBindingController<Boolean> bindingController,
                                           boolean initialValue) {
        super(eventController, bindingController);
        mValue = new AtomicBoolean(initialValue);
    }

    public AtomicObservableBooleanProperty(EventController eventController,
                                              PropertyBindingController<Boolean> bindingController,
                                              boolean initialValue) {
        super(eventController, bindingController);
        mValue = new AtomicBoolean(initialValue);
    }

    public AtomicObservableBooleanProperty(ObservableEventController<Boolean> eventController,
                                           PropertyBindingController<Boolean> bindingController) {
        this(eventController, bindingController, false);
    }

    @Override
    protected void setInternalDirect(Boolean value) {
        mValue.set(value);
    }

    @Override
    public void setInternal(boolean value) {
        boolean oldValue = mValue.getAndSet(value);
        if (oldValue != value) {
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public boolean getInternal() {
        return mValue.get();
    }
}
