package com.beans.observables.properties;

/**
 * <p>
 *     A simple implementation of {@link ObservableIntProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsInt(int)}
 *     and {@link #getAsInt()}.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class SimpleObservableIntProperty extends ObservableIntProperty {

    private int mValue;

    public SimpleObservableIntProperty(int initialValue) {
        super(initialValue, false);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public SimpleObservableIntProperty() {
        this(0);
    }

    @Override
    public void setAsInt(int value) {
        if (mValue != value) {
            mValue = value;
            fireValueChangedEvent(value);
        }
    }

    @Override
    public int getAsInt() {
        return mValue;
    }
}
