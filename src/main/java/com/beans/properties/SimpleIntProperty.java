package com.beans.properties;

/**
 * <p>
 *     A simple implementation of {@link com.beans.IntProperty IntProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsInt(int)}
 *     and {@link #getAsInt()}.
 * </p>
 * <p>
 *     A boxed version of the access interface for writing and reading is available
 *     using {@link #set(Integer)} and {@link #get()}, which are basically proxies
 *     for the primitive interface.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class SimpleIntProperty extends IntPropertyBase {

    private int mValue;

    public SimpleIntProperty(int initialValue) {
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public SimpleIntProperty() {
        this(0);
    }

    @Override
    public void setAsInt(int value) {
        mValue = value;
    }

    @Override
    public int getAsInt() {
        return mValue;
    }
}
