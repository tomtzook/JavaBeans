package com.beans.observables.properties;

import com.beans.observables.listeners.ChangeListener;

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

    private ObservableListeningHelper<T> mObservableListeningHelper;

    /**
     * Initializes the listeners handling.
     *
     * @param threadSafe true to use a thread-safe listener added. Only affects {@link #addChangeListener(ChangeListener)}
     *                   {@link #removeChangeListener(ChangeListener)}, but should be used when this
     *                   class is accessed concurrently. {@link #fireValueChangedEvent(Object, Object)}
     *                   must be synchronized manually.
     */
    protected ObservablePropertyBase(boolean threadSafe) {
        mObservableListeningHelper = threadSafe
                ? ObservableListeningHelper.createSynchronized(this)
                : ObservableListeningHelper.createSimple(this);
    }

    @Override
    public final void addChangeListener(ChangeListener<? super T> changeListener) {
        mObservableListeningHelper.addListener(changeListener);
    }

    @Override
    public final void removeChangeListener(ChangeListener<? super T> changeListener) {
        mObservableListeningHelper.removeListener(changeListener);
    }

    /**
     * Invokes all added listeners, notifying them that the value has changed.
     *
     * @param oldValue the old value of the property, before the change.
     * @param newValue the new value of the property.
     */
    protected final void fireValueChangedEvent(T oldValue, T newValue) {
        mObservableListeningHelper.fireValueChangedEvent(oldValue, newValue);
    }

    @Override
    public String toString() {
        return String.format("ObservableProperty [value=%s]", String.valueOf(get()));
    }
}
