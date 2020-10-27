package com.beans.observables.properties;

import com.beans.observables.binding.AtomicPropertyBindingController;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.notifier.Controllers;
import com.notifier.EventController;

/**
 * <p>
 *     A simple implementation of {@link ObservableDoubleProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsDouble(double)}
 *     and {@link #getAsDouble()}.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class SimpleObservableDoubleProperty extends ObservableDoublePropertyBase {

    private double mValue;

    public SimpleObservableDoubleProperty(ObservableEventController<Double> eventController,
                                          PropertyBindingController<Double> bindingController,
                                          double initialValue) {
        super(eventController, bindingController);
        mValue = initialValue;
    }

    public SimpleObservableDoubleProperty(EventController eventController,
                                              PropertyBindingController<Double> bindingController,
                                              double initialValue) {
        super(eventController, bindingController);
        mValue = initialValue;
    }

    public SimpleObservableDoubleProperty(ObservableEventController<Double> eventController,
                                          PropertyBindingController<Double> bindingController) {
        this(eventController, bindingController, 0.0);
    }

    public SimpleObservableDoubleProperty(EventController eventController, double initialValue) {
        this(eventController, new AtomicPropertyBindingController<>(), initialValue);
    }

    public SimpleObservableDoubleProperty(EventController eventController) {
        this(eventController, 0.0);
    }

    public SimpleObservableDoubleProperty(double initialValue) {
        this(Controllers.newSyncExecutionController(), initialValue);
    }

    public SimpleObservableDoubleProperty() {
        this(0.0);
    }

    @Override
    protected void setInternalDirect(Double value) {
        mValue = value;
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
