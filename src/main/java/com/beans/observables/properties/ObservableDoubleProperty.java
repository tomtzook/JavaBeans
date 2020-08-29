package com.beans.observables.properties;

import com.beans.DoubleProperty;
import com.beans.observables.ObservableDoubleValue;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.notifier.EventController;

import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 *     A <em>double</em> specialization of {@link ObservableProperty}.
 *     Provides methods to access using primitive types: {@link #getAsDouble()}, {@link #setAsDouble(double)}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     Implements {@link #set(Double)} and {@link #get()} as proxy calls to {@link #setAsDouble(double)}
 *     and {@link #getAsDouble()} respectively.
 * </p>
 * <p>
 *     This property is <b>not</b> <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public abstract class ObservableDoubleProperty extends ObservablePropertyBase<Double>
        implements ObservableDoubleValue, DoubleProperty {

    protected ObservableDoubleProperty(ObservableEventController<Double> eventController,
                                       PropertyBindingController<Double> bindingController) {
        super(eventController, bindingController);
    }

    protected ObservableDoubleProperty(EventController eventController,
                                        PropertyBindingController<Double> bindingController) {
        super(eventController, bindingController);
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
