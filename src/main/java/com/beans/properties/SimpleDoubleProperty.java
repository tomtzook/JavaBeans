package com.beans.properties;

/**
 * <p>
 *     A simple implementation of {@link com.beans.DoubleProperty DoubleProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsDouble(double)}
 *     and {@link #getAsDouble()}.
 * </p>
 * <p>
 *     A boxed version of the access interface for writing and reading is available
 *     using {@link #set(Double)} and {@link #get()}, which are basically proxies
 *     for the primitive interface.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class SimpleDoubleProperty extends DoublePropertyBase {
    
    private double mValue;

    public SimpleDoubleProperty(double initialValue) {
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public SimpleDoubleProperty() {
        this(0.0);
    }

    @Override
    public void setAsDouble(double value) {
        mValue = value;
    }

    @Override
    public double getAsDouble() {
        return mValue;
    }

    @Override
    public String toString() {
        return String.valueOf(mValue);
    }
}
