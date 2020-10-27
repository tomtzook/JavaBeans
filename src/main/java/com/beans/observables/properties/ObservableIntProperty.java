package com.beans.observables.properties;

import com.beans.IntProperty;
import com.beans.observables.ObservableIntValue;

/**
 * <p>
 *     A <em>int</em> specialization of {@link ObservableProperty}.
 *     Provides methods to access using primitive types: {@link #getAsInt()}, {@link #setAsInt(int)}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     Implements {@link #set(Integer)} and {@link #get()} as proxy calls to {@link #setAsInt(int)}
 *     and {@link #getAsInt()} respectively.
 * </p>
 * <p>
 *     This property is <b>not</b> <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public interface ObservableIntProperty
        extends ObservableProperty<Integer>, ObservableIntValue, IntProperty {
}
