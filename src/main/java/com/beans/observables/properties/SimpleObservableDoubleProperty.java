package com.beans.observables.properties;

import com.beans.observables.binding.PropertyBindingController;
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

    public SimpleObservableDoubleProperty(ObservableEventController<Double> eventController,
                                          PropertyBindingController<Double> bindingController,
                                          double initialValue) {
        super(eventController, bindingController);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public SimpleObservableDoubleProperty(ObservableEventController<Double> eventController,
                                          PropertyBindingController<Double> bindingController) {
        this(eventController, bindingController, 0.0);
    }

    @Override
    public void setInternal(double value) {
        if (mValue != value) {
            double oldValue = mValue;
            mValue = value;
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public double getInternal() {
        return mValue;
    }
}
