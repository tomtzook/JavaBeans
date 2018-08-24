package com.beans.observables;

import java.util.function.IntSupplier;

/**
 * <p>
 *     Am <em>int</em> specialization of {@link ObservableValue}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     An extension of {@link IntSupplier}.
 * </p>
 * <p>
 *     This observable is not <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public interface ObservableIntValue extends ObservableValue<Integer>, IntSupplier {
}
