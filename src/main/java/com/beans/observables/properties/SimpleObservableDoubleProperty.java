package com.beans.observables.properties;

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

    public SimpleObservableDoubleProperty(double initialValue) {
        super(initialValue, false);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public SimpleObservableDoubleProperty() {
        this(0.0);
    }

    @Override
    public void setAsDouble(double value) {
        if (mValue != value) {
            mValue = value;
            fireValueChangedEvent(value);
        }
    }

    @Override
    public double getAsDouble() {
        return mValue;
    }
}
