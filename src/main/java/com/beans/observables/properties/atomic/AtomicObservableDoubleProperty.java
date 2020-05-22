package com.beans.observables.properties.atomic;

import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.beans.observables.properties.ObservableDoubleProperty;
import com.notifier.EventController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link ObservableDoubleProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsDouble(double)}
 *     and {@link #getAsDouble()}.
 * </p>
 * <p>
 *     This implementation uses the <em>java.util.concurrent.atomic</em> package, to provide
 *     a lock-free, atomic read and write operations.
 * </p>
 * <p>
 *     In some cases, {@link AtomicLong} may old a lock, specifically, if the operating
 *     system does not support 64-bit operations.
 * </p>
 * <p>
 *     Depending on the {@link ObservableEventController} used, it is possible
 *     that changes from multiple threads won't dispatch in the correct order.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class AtomicObservableDoubleProperty extends ObservableDoubleProperty {

    private final AtomicLong mValue;

    public AtomicObservableDoubleProperty(ObservableEventController<Double> eventController,
                                          PropertyBindingController<Double> bindingController,
                                          double initialValue) {
        super(eventController, bindingController);
        mValue = new AtomicLong(Double.doubleToLongBits(initialValue));
    }

    public AtomicObservableDoubleProperty(EventController eventController,
                                              PropertyBindingController<Double> bindingController,
                                              double initialValue) {
        super(eventController, bindingController);
        mValue = new AtomicLong(Double.doubleToLongBits(initialValue));
    }

    public AtomicObservableDoubleProperty(ObservableEventController<Double> eventController,
                                          PropertyBindingController<Double> bindingController) {
        this(eventController, bindingController, 0.0);
    }

    @Override
    protected void setInternalDirect(Double value) {
        mValue.set(Double.doubleToLongBits(value));
    }

    @Override
    protected void setInternal(double value) {
        long newLongValue = Double.doubleToLongBits(value);
        long oldLongValue = mValue.getAndSet(newLongValue);
        if (oldLongValue != newLongValue) {
            double oldValue = Double.longBitsToDouble(oldLongValue);
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    protected double getInternal() {
        return Double.longBitsToDouble(mValue.get());
    }
}
