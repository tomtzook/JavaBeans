package com.beans.observables.properties;

import com.beans.observables.listeners.ObservableEventController;

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

    public SimpleObservableLongProperty(ObservableEventController<Long> eventController, long initialValue) {
        super(eventController);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public SimpleObservableLongProperty(ObservableEventController<Long> eventController) {
        this(eventController, 0);
    }

    @Override
    public void setAsLong(long value) {
        if (mValue != value) {
            long oldValue = mValue;
            mValue = value;
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public long getAsLong() {
        return mValue;
    }
}
