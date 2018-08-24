package com.beans.observables.properties;

import com.beans.Property;
import com.beans.observables.ObservableValue;

/**
 * <p>
 *     A mutable property. Provides access to a value which could be modified by other sources.
 *     Listeners can be attached to listen for changes of the value. Calling {@link #set(Object)}
 *     will invoke any added listeners.
 * </p>
 * <p>
 *     A property is <em>nullable</em>.
 * </p>
 *
 * @param <T> type of data
 *
 * @since JavaBeans 1.0
 */
public interface ObservableProperty<T> extends ObservableValue<T>, Property<T> {

    /**
     * {@inheritDoc}
     * <p>
     *     This call invokes any listeners which are added if it changes
     *     the value which is stored by the property.
     * </p>
     */
    @Override
    void set(T value);
}
