package com.beans.observables.properties.atomic;

import com.beans.observables.binding.AtomicPropertyBindingController;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.beans.observables.properties.ObservableBooleanProperty;
import com.beans.observables.properties.ObservableBooleanPropertyBase;
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
 * <p>
 *     Depending on the {@link ObservableEventController} used, it is possible
 *     that changes from multiple threads won't dispatch in the correct order.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class AtomicObservableBooleanProperty extends ObservableBooleanPropertyBase {

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

    public AtomicObservableBooleanProperty(EventController eventController, boolean initialValue) {
        this(eventController, new AtomicPropertyBindingController<>(), initialValue);
    }

    public AtomicObservableBooleanProperty(EventController eventController) {
        this(eventController, false);
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
