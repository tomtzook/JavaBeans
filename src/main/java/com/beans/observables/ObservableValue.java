package com.beans.observables;

import com.beans.observables.listeners.ChangeEvent;
import com.beans.observables.listeners.ChangeListener;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * <p>
 *     An observable value. Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     An extension of {@link Supplier}.
 * </p>
 * <p>
 *     An observable is <em>nullable</em>.
 * </p>
 *
 * @param <T> type of data of the value.
 *
 * @since JavaBeans 1.0
 */
public interface ObservableValue<T> extends Supplier<T> {

    /**
     * Adds a listener for changes of the value.
     * <p>
     *     Implementations of this interface are guaranteed to call
     *     {@link ChangeListener#onChange(ChangeEvent)}
     *     of attached listeners when the value has changed.
     * </p>
     *
     * @param changeListener listener to add.
     */
    void addChangeListener(ChangeListener<? super T> changeListener);

    /**
     * Removes a listener if it was added.
     *
     * @param changeListener listener to remove.
     */
    void removeChangeListener(ChangeListener<? super T> changeListener);

    void bind(ObservableValue<T> observableValue);
    void unbind();

    <T2> ObservableValue<T2> as(Function<T, T2> convertor);
}
