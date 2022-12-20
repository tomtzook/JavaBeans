package com.beans.observables.properties;

import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.notifier.EventController;

import java.util.Objects;
import java.util.Optional;

public abstract class ObservableDoublePropertyBase extends ObservablePropertyBase<Double>
        implements ObservableDoubleProperty {

    protected ObservableDoublePropertyBase(Object bean,
                                           ObservableEventController<Double> eventController,
                                           PropertyBindingController<Double> bindingController) {
        super(bean, eventController, bindingController);
    }

    protected ObservableDoublePropertyBase(ObservableEventController<Double> eventController,
                                           PropertyBindingController<Double> bindingController) {
        super(eventController, bindingController);
    }

    protected ObservableDoublePropertyBase(Object bean,
                                           EventController eventController) {
        super(bean, eventController);
    }

    protected ObservableDoublePropertyBase(EventController eventController) {
        super(eventController);
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This call invokes any listeners which are added if it changes
     *     the value which is stored by the property.
     * </p>
     */
    @Override
    public void setAsDouble(double value) {
        if (!setIfBound(value)) {
            setInternal(value);
        }
    }

    @Override
    public double getAsDouble() {
        Optional<Double> boundOptional = getIfBound();
        return boundOptional.orElseGet(this::getInternal);
    }

    @Override
    public void set(Double value) {
        Objects.requireNonNull(value, "value is null");

        if (!setIfBound(value)) {
            setInternal(value);
        }
    }

    @Override
    public Double get() {
        Optional<Double> boundOptional = getIfBound();
        return boundOptional.orElseGet(this::getInternal);

    }

    protected abstract void setInternal(double value);
    protected abstract double getInternal();

    @Override
    public String toString() {
        return String.format("ObservableDoubleProperty [value=%f]", getAsDouble());
    }
}
