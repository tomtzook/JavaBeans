package com.beans.observables.properties;

import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
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
public class SimpleObservableLongProperty extends ObservableLongProperty {

    private long mValue;

    public SimpleObservableLongProperty(ObservableEventController<Long> eventController,
                                        PropertyBindingController<Long> bindingController,
                                        long initialValue) {
        super(eventController, bindingController);
        mValue = initialValue;
    }

    public SimpleObservableLongProperty(EventController eventController,
                                              PropertyBindingController<Long> bindingController,
                                              long initialValue) {
        super(eventController, bindingController);
        mValue = initialValue;
    }

    public SimpleObservableLongProperty(ObservableEventController<Long> eventController,
                                        PropertyBindingController<Long> bindingController) {
        this(eventController, bindingController, 0);
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
