package com.beans.properties;

/**
 * <p>
 *     A simple implementation of {@link com.beans.LongProperty LongProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsLong(long)}
 *     and {@link #getAsLong()}.
 * </p>
 * <p>
 *     A boxed version of the access interface for writing and reading is available
 *     using {@link #set(Long)} and {@link #get()}, which are basically proxies
 *     for the primitive interface.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class SimpleLongProperty extends LongPropertyBase {

    private long mValue;

    public SimpleLongProperty(long initialValue) {
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>0</em>.
     */
    public SimpleLongProperty() {
        this(0);
    }

    @Override
    public void setAsLong(long value) {
        mValue = value;
    }

    @Override
    public long getAsLong() {
        return mValue;
    }
}
