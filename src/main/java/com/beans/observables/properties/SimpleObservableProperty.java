package com.beans.observables.properties;

import java.util.Objects;

/**
 * <p>
 *     A simple implementation of {@link ObservableProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #set(Object)}
 *     and {@link #get()}.
 * </p>
 *
 * @param <T> type of the property data.
 *
 * @since JavaBeans 1.0
 */
public class SimpleObservableProperty<T> extends ObservablePropertyBase<T> {

    private T mValue;

    public SimpleObservableProperty(T initialValue) {
        super(initialValue, false);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>null</em>.
     */
    public SimpleObservableProperty() {
        this(null);
    }

    @Override
    public void set(T value) {
        if (!Objects.equals(mValue, value)) {
            mValue = value;
            fireValueChangedEvent(value);
        }
    }

    @Override
    public T get() {
        return mValue;
    }
}
