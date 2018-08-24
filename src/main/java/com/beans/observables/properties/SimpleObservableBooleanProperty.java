package com.beans.observables.properties;

/**
 * <p>
 *     A simple implementation of {@link ObservableBooleanProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsBoolean(boolean)}
 *     and {@link #getAsBoolean()}.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class SimpleObservableBooleanProperty extends ObservableBooleanProperty {

    private boolean mValue;

    public SimpleObservableBooleanProperty(boolean initialValue) {
        super(false);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>false</em>.
     */
    public SimpleObservableBooleanProperty() {
        this(false);
    }

    @Override
    public void setAsBoolean(boolean value) {
        if (mValue != value) {
            boolean oldValue = mValue;
            mValue = value;
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public boolean getAsBoolean() {
        return mValue;
    }
}
