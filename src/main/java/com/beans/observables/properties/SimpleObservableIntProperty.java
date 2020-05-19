package com.beans.observables.properties;

import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
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
public class SimpleObservableIntProperty extends ObservableIntProperty {

    private int mValue;

    public SimpleObservableIntProperty(ObservableEventController<Integer> eventController,
                                       PropertyBindingController<Integer> bindingController,
                                       int initialValue) {
        super(eventController, bindingController);
        mValue = initialValue;
    }

    public SimpleObservableIntProperty(EventController eventController,
                                              PropertyBindingController<Integer> bindingController,
                                              int initialValue) {
        super(eventController, bindingController);
        mValue = initialValue;
    }

    public SimpleObservableIntProperty(ObservableEventController<Integer> eventController,
                                       PropertyBindingController<Integer> bindingController) {
        this(eventController, bindingController, 0);
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
