package com.beans.observables.properties;

import com.beans.observables.binding.AtomicPropertyBindingController;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.notifier.Controllers;
import com.notifier.EventController;

/**
 * <p>
 *     A simple implementation of {@link ObservableLongProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsLong(long)}
 *     and {@link #getAsLong()}.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class SimpleObservableLongProperty extends ObservableLongPropertyBase {

    private long mValue;

    public SimpleObservableLongProperty(Object bean,
                                       EventController eventController,
                                       long initialValue) {
        super(bean, eventController);
        mValue = initialValue;
    }

    public SimpleObservableLongProperty(EventController eventController,
                                        long initialValue) {
        super(eventController);
        mValue = initialValue;
    }

    @Override
    protected void setInternalDirect(Long value) {
        mValue = value;
    }

    @Override
    public void setInternal(long value) {
        if (mValue != value) {
            long oldValue = mValue;
            mValue = value;
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public long getInternal() {
        return mValue;
    }
}
