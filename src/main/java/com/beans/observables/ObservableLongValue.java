package com.beans.observables;

import java.util.function.LongSupplier;

/**
 * <p>
 *     A <em>long</em> specialization of {@link ObservableValue}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     An extension of {@link LongSupplier}.
 * </p>
 * <p>
 *     This observable is not <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public interface ObservableLongValue extends ObservableValue<Long>, LongSupplier {
}
