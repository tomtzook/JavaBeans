package com.beans.observables.properties;

import com.beans.BooleanProperty;
import com.beans.observables.ObservableBooleanValue;

/**
 * <p>
 *     A <em>boolean</em> specialization of {@link ObservableProperty}.
 *     Provides methods to access using primitive types: {@link #getAsBoolean()}, {@link #setAsBoolean(boolean)}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     Implements {@link #set(Boolean)} and {@link #get()} as proxy calls to {@link #setAsBoolean(boolean)}
 *     and {@link #getAsBoolean()} respectively.
 * </p>
 * <p>
 *     This property is <b>not</b> <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public interface ObservableBooleanProperty
        extends ObservableProperty<Boolean>, ObservableBooleanValue, BooleanProperty {

}
