package com.beans.observables.properties;

import com.beans.LongProperty;
import com.beans.observables.ObservableLongValue;

/**
 * <p>
 *     A <em>long</em> specialization of {@link ObservableProperty}.
 *     Provides methods to access using primitive types: {@link #getAsLong()}, {@link #setAsLong(long)}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     Implements {@link #set(Long)} and {@link #get()} as proxy calls to {@link #setAsLong(long)}
 *     and {@link #getAsLong()} respectively.
 * </p>
 * <p>
 *     This property is <b>not</b> <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public interface ObservableLongProperty
        extends ObservableProperty<Long>, ObservableLongValue, LongProperty {
}
