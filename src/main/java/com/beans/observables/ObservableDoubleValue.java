package com.beans.observables;

import java.util.function.DoubleSupplier;

/**
 * <p>
 *     A <em>double</em> specialization of {@link ObservableValue}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     An extension of {@link DoubleSupplier}.
 * </p>
 * <p>
 *     This observable is not <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public interface ObservableDoubleValue extends ObservableValue<Double>, DoubleSupplier {
}
