package com.beans.observables.properties.atomic;

import com.beans.observables.listeners.ObservableEventController;
import com.beans.observables.properties.ObservablePropertyBase;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *     A <b>thread-safe</b> implementation of {@link com.beans.observables.properties.ObservableProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #set(Object)}
 *     and {@link #get()}.
 * </p>
 * <p>
 *     This implementation uses the <em>java.util.concurrent.atomic</em> package, to provide
 *     a lock-free, atomic read and write operations.
 * </p>
 *
 * @param <T> type of the property data.
 *
 * @since JavaBeans 1.0
 */
public class AtomicObservableProperty<T> extends ObservablePropertyBase<T> {

    private final AtomicReference<T> mValue;

    public AtomicObservableProperty(ObservableEventController<T> eventController, T initialValue) {
        super(eventController);
        mValue = new AtomicReference<>(initialValue);
    }

    /**
     * Initializes the property with a value of <em>null</em>.
     */
    public AtomicObservableProperty(ObservableEventController<T> eventController) {
        this(eventController, null);
    }

    @Override
    public void set(T value) {
        T oldValue = mValue.getAndSet(value);
        if (!Objects.equals(oldValue, value)) {
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public T get() {
        return mValue.get();
    }
}
