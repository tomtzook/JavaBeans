package com.beans.observables;

import com.beans.observables.listeners.ChangeEvent;
import com.beans.observables.listeners.ChangeListener;
import com.beans.util.function.OneWayConverter;
import com.beans.util.function.ToBooleanConverter;
import com.beans.util.function.ToDoubleConverter;
import com.beans.util.function.ToIntConverter;
import com.beans.util.function.ToLongConverter;
import com.notifier.RegisteredListener;

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
     * Gets the BEAN attached to this object. Usually this represents
     * an object whose properties/fields are represented by this observable.
     *
     * @return the BEAN, or <i>null</i> if not set.
     */
    Object getBean();

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
    RegisteredListener addChangeListener(ChangeListener<? super T> changeListener);

    void bind(ObservableValue<T> observableValue);
    void unbind();

    <T2> ObservableValue<T2> as(OneWayConverter<T, T2> converter);
    ObservableBooleanValue asBoolean(ToBooleanConverter<T> converter);
    ObservableIntValue asInt(ToIntConverter<T> converter);
    ObservableLongValue asLong(ToLongConverter<T> converter);
    ObservableDoubleValue asDouble(ToDoubleConverter<T> converter);
}
