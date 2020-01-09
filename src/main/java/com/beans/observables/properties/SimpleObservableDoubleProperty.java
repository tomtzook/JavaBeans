package com.beans.observables.properties;

import com.beans.observables.listeners.ObservableEventController;

/**
 * <p>
 *     A simple implementation of {@link ObservableDoubleProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsDouble(double)}
 *     and {@link #getAsDouble()}.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class SimpleObservableDoubleProperty extends ObservableDoubleProperty {

    private double mValue;

    public SimpleObservableDoubleProperty(ObservableEventController<Double> eventController, double initialValue) {
        super(eventController);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public SimpleObservableDoubleProperty(ObservableEventController<Double> eventController) {
        this(eventController, 0.0);
    }

    @Override
    public void setAsDouble(double value) {
        if (mValue != value) {
            double oldValue = mValue;
            mValue = value;
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public double getAsDouble() {
        return mValue;
    }
}
