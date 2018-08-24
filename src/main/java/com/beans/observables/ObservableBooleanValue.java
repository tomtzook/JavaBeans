package com.beans.observables;

import java.util.function.BooleanSupplier;

/**
 * <p>
 *     A <em>boolean</em> specialization of {@link ObservableValue}.
 *     Listeners can be attached to listen for changes of the value.
 * </p>
 * <p>
 *     An extension of {@link BooleanSupplier}.
 * </p>
 * <p>
 *     This observable is not <em>nullable</em>.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public interface ObservableBooleanValue extends ObservableValue<Boolean>, BooleanSupplier {
}
