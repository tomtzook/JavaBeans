package com.beans.observables.properties;

import com.beans.BooleanProperty;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;

import java.util.Objects;
import java.util.Optional;

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
public abstract class ObservableBooleanProperty extends ObservablePropertyBase<Boolean> implements BooleanProperty {

    protected ObservableBooleanProperty(ObservableEventController<Boolean> eventController,
                                        PropertyBindingController<Boolean> bindingController) {
        super(eventController, bindingController);
    }

    /**
     * {@inheritDoc}
     * <p>
     *     This call invokes any listeners which are added if it changes
     *     the value which is stored by the property.
     * </p>
     */
    @Override
    public void setAsBoolean(boolean value) {
        if (!setIfBound(value)) {
            setInternal(value);
        }
    }

    @Override
    public boolean getAsBoolean() {
        Optional<Boolean> boundOptional = getIfBound();
        return boundOptional.orElseGet(this::getInternal);
    }

    @Override
    public void set(Boolean value) {
        Objects.requireNonNull(value, "value is null");

        if (!setIfBound(value)) {
            setInternal(value);
        }
    }

    @Override
    public Boolean get() {
        Optional<Boolean> boundOptional = getIfBound();
        return boundOptional.orElseGet(this::getInternal);

    }

    protected abstract void setInternal(boolean value);
    protected abstract boolean getInternal();

    @Override
    public String toString() {
        return String.format("ObservableBooleanProperty [value=%b]", getAsBoolean());
    }
}
