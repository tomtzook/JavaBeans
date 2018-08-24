package com.beans.properties;

/**
 * <p>
 *     A simple implementation of {@link com.beans.Property Property}, holding a
 *     variable which is accessed for writing or reading through {@link #set(Object)}
 *     and {@link #get()}.
 * </p>
 *
 * @param <T> type of the property data.
 *
 * @since JavaBeans 1.0
 */
public class SimpleProperty<T> extends PropertyBase<T> {

    private T mValue;

    public SimpleProperty(T initialValue) {
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>null</em>.
     */
    public SimpleProperty() {
        this(null);
    }

    @Override
    public void set(T value) {
        mValue = value;
    }

    @Override
    public T get() {
        return mValue;
    }
}
