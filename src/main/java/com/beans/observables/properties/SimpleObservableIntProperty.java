package com.beans.observables.properties;

import com.beans.observables.binding.AtomicPropertyBindingController;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.notifier.Controllers;
import com.notifier.EventController;

/**
 * <p>
 *     A simple implementation of {@link ObservableIntProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsInt(int)}
 *     and {@link #getAsInt()}.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class SimpleObservableIntProperty extends ObservableIntPropertyBase {

    private int mValue;

    public SimpleObservableIntProperty(Object bean,
                                       EventController eventController,
                                       int initialValue) {
        super(bean, eventController);
        mValue = initialValue;
    }

    public SimpleObservableIntProperty(EventController eventController,
                                       int initialValue) {
        super(eventController);
        mValue = initialValue;
    }

    @Override
    protected void setInternalDirect(Integer value) {
        mValue = value;
    }

    @Override
    public void setInternal(int value) {
        if (mValue != value) {
            int oldValue = mValue;
            mValue = value;
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public int getInternal() {
        return mValue;
    }
}
