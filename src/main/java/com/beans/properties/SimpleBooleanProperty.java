package com.beans.properties;

/**
 * <p>
 *     A simple implementation of {@link com.beans.BooleanProperty BooleanProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsBoolean(boolean)}
 *     and {@link #getAsBoolean()}.
 * </p>
 * <p>
 *     A boxed version of the access interface for writing and reading is available
 *     using {@link #set(Boolean)} and {@link #get()}, which are basically proxies
 *     for the primitive interface.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class SimpleBooleanProperty extends BooleanPropertyBase {

    private boolean mValue;

    public SimpleBooleanProperty(boolean initialValue) {
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>false</em>.
     */
    public SimpleBooleanProperty() {
        this(false);
    }

    @Override
    public void setAsBoolean(boolean value) {
        mValue = value;
    }

    @Override
    public boolean getAsBoolean() {
        return mValue;
    }
}
