package com.beans.observables.properties;

import com.beans.observables.binding.AtomicPropertyBindingController;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.notifier.Controllers;
import com.notifier.EventController;

import java.util.Objects;
import java.util.Optional;

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

    public SimpleObservableProperty(ObservableEventController<T> eventController,
                                    PropertyBindingController<T> bindingController,
                                    T initialValue) {
        super(eventController, bindingController);
        mValue = initialValue;
    }

    public SimpleObservableProperty(EventController eventController,
                                    PropertyBindingController<T> bindingController,
                                    T initialValue) {
        super(eventController, bindingController);
        mValue = initialValue;
    }

    public SimpleObservableProperty(ObservableEventController<T> eventController,
                                    PropertyBindingController<T> bindingController) {
        this(eventController, bindingController, null);
    }

    public SimpleObservableProperty(EventController eventController, T initialValue) {
        this(eventController, new AtomicPropertyBindingController<>(), initialValue);
    }

    public SimpleObservableProperty(EventController eventController) {
        this(eventController, null);
    }

    public SimpleObservableProperty(T initialValue) {
        this(Controllers.newSyncExecutionController(), initialValue);
    }

    public SimpleObservableProperty() {
        this((T) null);
    }

    @Override
    protected void setInternalDirect(T value) {
        mValue = value;
    }

    @Override
    public void set(T value) {
        if (!setIfBound(value)) {
            if (!Objects.equals(mValue, value)) {
                T oldValue = mValue;
                mValue = value;
                fireValueChangedEvent(oldValue, value);
            }
        }
    }

    @Override
    public T get() {
        Optional<T> boundOptional = getIfBound();
        return boundOptional.orElse(mValue);
    }
}
