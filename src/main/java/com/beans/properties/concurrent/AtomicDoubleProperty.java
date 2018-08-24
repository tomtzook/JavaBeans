package com.beans.properties.concurrent;

import com.beans.properties.DoublePropertyBase;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link com.beans.DoubleProperty DoubleProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsDouble(double)}
 *     and {@link #getAsDouble()}.
 * </p>
 * <p>
 *     A boxed version of the access interface for writing and reading is available
 *     using {@link #set(Double)} and {@link #get()}, which are basically proxies
 *     for the primitive interface.
 * </p>
 * <p>
 *     This implementation relays on the fact that the Java Language Specifications guarantee that an access (read/write) 
 *     to a <em>volatile</em> field is atomic, and will be visible to all threads.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class AtomicDoubleProperty extends DoublePropertyBase {

    private volatile double mValue;

    public AtomicDoubleProperty(double value) {
        mValue = value;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public AtomicDoubleProperty() {
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
