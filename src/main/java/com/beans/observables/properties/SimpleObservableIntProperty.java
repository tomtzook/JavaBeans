package com.beans.observables.properties;

import com.beans.observables.listeners.ObservableEventController;

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

    public SimpleObservableIntProperty(ObservableEventController<Integer> eventController, int initialValue) {
        super(eventController);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public SimpleObservableIntProperty(ObservableEventController<Integer> eventController) {
        this(eventController, 0);
    }

    @Override
    public void setAsInt(int value) {
        if (mValue != value) {
            int oldValue = mValue;
            mValue = value;
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public int getAsInt() {
        return mValue;
    }
}
