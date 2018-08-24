package com.beans.observables.listeners;

import com.beans.observables.ObservableValue;

/**
 * <p>
 *      Listener for value change events of {@link ObservableValue}.
 * </p>
 *
 * @param <T> type of data of the value.
 *
 * @since JavaBeans 1.0
 */
public interface ChangeListener<T> {

    /**
     * Called when an {@link ObservableValue}, this listener
     * was added to, was changed.
     *
     * @param observable the observable that was changed.
     * @param oldValue the old value.
     * @param newValue the new value.
     */
    void changed(ObservableValue<? extends T> observable, T oldValue, T newValue);
}
