package com.beans.observables.listeners;

import com.beans.observables.ObservableValue;
import com.notifier.Listener;

/**
 * <p>
 *      Listener for value change events of {@link ObservableValue}.
 * </p>
 *
 * @param <T> type of data of the value.
 *
 * @since JavaBeans 1.0
 */
public interface ChangeListener<T> extends Listener {

    /**
     * Called when an {@link ObservableValue}, this listener
     * was added to, was changed.
     *
     * @param event change event
     */
    void onChange(ChangeEvent<T> event);
}
