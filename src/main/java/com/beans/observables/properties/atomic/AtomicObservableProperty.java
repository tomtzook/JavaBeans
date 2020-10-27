package com.beans.observables.properties.atomic;

import com.beans.observables.binding.AtomicPropertyBindingController;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.beans.observables.properties.ObservablePropertyBase;
import com.notifier.EventController;

import java.util.Objects;
import java.util.Optional;
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
 * <p>
 *     Depending on the {@link ObservableEventController} used, it is possible
 *     that changes from multiple threads won't dispatch in the correct order.
 * </p>
 *
 * @param <T> type of the property data.
 *
 * @since JavaBeans 1.0
 */
public class AtomicObservableProperty<T> extends ObservablePropertyBase<T> {

    private final AtomicReference<T> mValue;

    public AtomicObservableProperty(ObservableEventController<T> eventController,
                                    PropertyBindingController<T> bindingController,
                                    T initialValue) {
        super(eventController, bindingController);
        mValue = new AtomicReference<>(initialValue);
    }

    public AtomicObservableProperty(EventController eventController,
                                        PropertyBindingController<T> bindingController,
                                        T initialValue) {
        super(eventController, bindingController);
        mValue = new AtomicReference<>(initialValue);
    }

    public AtomicObservableProperty(ObservableEventController<T> eventController,
                                    PropertyBindingController<T> bindingController) {
        this(eventController, bindingController, null);
    }

    public AtomicObservableProperty(EventController eventController, T initialValue) {
        this(eventController, new AtomicPropertyBindingController<>(), initialValue);
    }

    public AtomicObservableProperty(EventController eventController) {
        this(eventController, null);
    }

    @Override
    protected void setInternalDirect(T value) {
        mValue.set(value);
    }

    @Override
    public void set(T value) {
        if (!setIfBound(value)) {
            T oldValue = mValue.getAndSet(value);
            if (!Objects.equals(oldValue, value)) {
                fireValueChangedEvent(oldValue, value);
            }
        }
    }

    @Override
    public T get() {
        Optional<T> boundOptional = getIfBound();
        return boundOptional.orElseGet(mValue::get);
    }
}
