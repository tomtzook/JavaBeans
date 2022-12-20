package com.beans.observables.properties;

import com.beans.observables.ObservableBooleanValue;
import com.beans.observables.ObservableDoubleValue;
import com.beans.observables.ObservableIntValue;
import com.beans.observables.ObservableLongValue;
import com.beans.observables.ObservableValue;
import com.beans.observables.RegisteredListener;
import com.beans.observables.binding.AtomicPropertyBindingController;
import com.beans.observables.binding.ObservableBinding;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.converted.ConvertedObservable;
import com.beans.observables.converted.ToBooleanConvertedObservable;
import com.beans.observables.converted.ToDoubleConvertedObservable;
import com.beans.observables.converted.ToIntConvertedObservable;
import com.beans.observables.converted.ToLongConvertedObservable;
import com.beans.observables.listeners.ChangeEvent;
import com.beans.observables.listeners.ChangeListener;
import com.beans.observables.listeners.EventControllerImpl;
import com.beans.observables.listeners.ObservableEventController;
import com.beans.util.function.OneWayConverter;
import com.beans.util.function.ToBooleanConverter;
import com.beans.util.function.ToDoubleConverter;
import com.beans.util.function.ToIntConverter;
import com.beans.util.function.ToLongConverter;
import com.notifier.EventController;

import java.lang.ref.WeakReference;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * <p>
 *     Base for {@link ObservableProperty} implementations.
 * </p>
 * <p>
 *     Provides implementations for {@link #addChangeListener(ChangeListener)}, {@link #removeChangeListener(ChangeListener)}.
 *     Provides {@link #fireValueChangedEvent(Object, Object)} for extending classes to fire changed event.
 * </p>
 * <p>
 *     This class can be <b>thread-safe</b> by defining so in the constructor.
 *
 *     The thread safety manifest in the manner of accessing the list of added
 *     listener, making {@link #addChangeListener(ChangeListener)} and {@link #removeChangeListener(ChangeListener)}
 *     <b>thread-safe</b>. But, although {@link #fireValueChangedEvent(Object, Object)} is safe
 *     in accessing the listener list, it <b>does not</b> limit threads from firing the event multiple times concurrently.
 * </p>
 *
 * @param <T> type of the property data
 *
 * @since JavaBeans 1.0
 */
public abstract class ObservablePropertyBase<T> implements ObservableProperty<T> {

    private final Object mBean;
    private final ObservableEventController<T> mEventController;
    private final PropertyBindingController<T> mBindingController;

    protected ObservablePropertyBase(Object bean,
                                     ObservableEventController<T> eventController,
                                     PropertyBindingController<T> bindingController) {
        mBean = bean;
        mEventController = eventController;
        mBindingController = bindingController;
    }

    protected ObservablePropertyBase(ObservableEventController<T> eventController,
                                     PropertyBindingController<T> bindingController) {
        this(null, eventController, bindingController);
    }

    protected ObservablePropertyBase(Object bean, EventController eventController) {
        mBean = bean;
        mEventController = new EventControllerImpl<>(eventController, this);
        mBindingController = new AtomicPropertyBindingController<>();
    }

    protected ObservablePropertyBase(EventController eventController) {
        this(null, eventController);
    }

    @Override
    public Object getBean() {
        return mBean;
    }

    @Override
    public final RegisteredListener addChangeListener(ChangeListener<? super T> changeListener) {
        return mEventController.addListener(changeListener);
    }

    @Override
    public final void bind(ObservableValue<T> observableValue) {
        mBindingController.bind(observableValue, new BoundChangeHandler<>(this));
    }

    @Override
    public final void bindBidirectional(ObservableProperty<T> observableProperty) {
        mBindingController.bindBidirectional(observableProperty, new BoundChangeHandler<>(this));
    }

    @Override
    public final void unbind() {
        Optional<ObservableBinding<T>> optional = mBindingController.unbind();
        if (optional.isPresent()) {
            ObservableBinding<T> binding = optional.get();
            setInternalDirect(binding.get());
        }
    }

    @Override
    public <T2> ObservableValue<T2> as(OneWayConverter<T, T2> converter) {
        return new ConvertedObservable<>(this, converter);
    }

    @Override
    public ObservableBooleanValue asBoolean(ToBooleanConverter<T> converter) {
        return new ToBooleanConvertedObservable<>(this, converter);
    }

    @Override
    public ObservableIntValue asInt(ToIntConverter<T> converter) {
        return new ToIntConvertedObservable<>(this, converter);
    }

    @Override
    public ObservableLongValue asLong(ToLongConverter<T> converter) {
        return new ToLongConvertedObservable<>(this, converter);
    }

    @Override
    public ObservableDoubleValue asDouble(ToDoubleConverter<T> converter) {
        return new ToDoubleConvertedObservable<>(this, converter);
    }

    /**
     * Invokes all added listeners, notifying them that the value has changed.
     *
     * @param oldValue the old value of the property, before the change.
     * @param newValue the new value of the property.
     */
    protected final void fireValueChangedEvent(T oldValue, T newValue) {
        mEventController.fire(new ChangeEvent<>(this, oldValue, newValue));
    }

    protected final boolean isBound() {
        return mBindingController.isBound();
    }

    protected final Optional<ObservableBinding<T>> getBound() {
        return mBindingController.getBinding();
    }

    protected final boolean setIfBound(T value) {
        Optional<ObservableBinding<T>> bindingOptional = getBound();
        if (bindingOptional.isPresent()) {
            bindingOptional.get().set(value);
            return true;
        }

        return false;
    }

    protected final Optional<T> getIfBound() {
        Optional<ObservableBinding<T>> bindingOptional = getBound();
        if (bindingOptional.isPresent()) {
            T value = bindingOptional.get().get();
            setInternalDirect(value);
            return Optional.of(value);
        }

        return Optional.empty();
    }

    protected abstract void setInternalDirect(T value);

    @Override
    public String toString() {
        return String.format("ObservableProperty [value=%s]", String.valueOf(get()));
    }

    private static class BoundChangeHandler<T> implements Consumer<ChangeEvent<T>> {

        private final WeakReference<ObservablePropertyBase<T>> mPropertyBase;

        private BoundChangeHandler(ObservablePropertyBase<T> propertyBase) {
            mPropertyBase = new WeakReference<>(propertyBase);
        }

        @Override
        public void accept(ChangeEvent<T> event) {
            ObservablePropertyBase<T> propertyBase = mPropertyBase.get();
            if (propertyBase == null) {
                return;
            }

            propertyBase.fireValueChangedEvent(event.getOldValue(), event.getNewValue());
        }
    }
}
