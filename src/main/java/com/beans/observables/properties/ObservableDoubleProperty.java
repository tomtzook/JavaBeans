package com.beans.observables.properties;

import com.beans.DoubleProperty;
import com.beans.observables.ObservableDoubleValue;

/**
 * <p>
 *     A <em>double</em> specialization of {@link ObservableProperty}.
 *     Provides methods to access using primitive types: {@link #getAsDouble()}, {@link #setAsDouble(double)}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     Implements {@link #set(Double)} and {@link #get()} as proxy calls to {@link #setAsDouble(double)}
 *     and {@link #getAsDouble()} respectively.
 * </p>
 * <p>
 *     This property is <b>not</b> <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public interface ObservableDoubleProperty
        extends ObservableProperty<Double>, ObservableDoubleValue, DoubleProperty{ //
}
