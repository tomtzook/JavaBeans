package com.beans.observables.properties;

import com.beans.observables.listeners.ObservableEventController;

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

    public SimpleObservableProperty(ObservableEventController<T> eventController, T initialValue) {
        super(eventController);
        mValue = initialValue;
    }

    /**
     * Initializes the property with a value of <em>null</em>.
     */
    public SimpleObservableProperty(ObservableEventController<T> eventController) {
        this(eventController,null);
    }

    @Override
    public void set(T value) {
        if (!Objects.equals(mValue, value)) {
            T oldValue = mValue;
            mValue = value;
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public T get() {
        return mValue;
    }
}
