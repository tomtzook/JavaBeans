package com.beans.observables.properties;

import com.beans.observables.listeners.ObservableEventController;

/**
 * <p>
 *     A simple implementation of {@link ObservableBooleanProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsBoolean(boolean)}
 *     and {@link #getAsBoolean()}.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class SimpleObservableBooleanProperty extends ObservableBooleanProperty {

    private boolean mValue;

    public SimpleObservableBooleanProperty(ObservableEventController<Boolean> eventController, boolean initialValue) {
        super(eventController);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>false</em>.
     */
    public SimpleObservableBooleanProperty(ObservableEventController<Boolean> eventController) {
        this(eventController, false);
    }

    @Override
    public void setAsBoolean(boolean value) {
        if (mValue != value) {
            boolean oldValue = mValue;
            mValue = value;
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public boolean getAsBoolean() {
        return mValue;
    }
}
