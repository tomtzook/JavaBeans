package com.beans.observables.properties;

import com.beans.observables.binding.AtomicPropertyBindingController;
import com.beans.observables.binding.PropertyBindingController;
import com.beans.observables.listeners.ObservableEventController;
import com.notifier.Controllers;
import com.notifier.EventController;

/**
 * <p>
 *     A simple implementation of {@link ObservableBooleanProperty}, holding a
 *     variable which is accessed for writing or reading through {@link #setAsBoolean(boolean)}
 *     and {@link #getAsBoolean()}.
 * </p>
 *
 * @since JavaBeans 1.0
 */
public class SimpleObservableBooleanProperty extends ObservableBooleanPropertyBase {

    private boolean mValue;

    public SimpleObservableBooleanProperty(ObservableEventController<Boolean> eventController,
                                           PropertyBindingController<Boolean> bindingController,
                                           boolean initialValue) {
        super(eventController, bindingController);
        mValue = initialValue;
    }

    public SimpleObservableBooleanProperty(EventController eventController,
                                        PropertyBindingController<Boolean> bindingController,
                                              boolean initialValue) {
        super(eventController, bindingController);
        mValue = initialValue;
    }

    public SimpleObservableBooleanProperty(ObservableEventController<Boolean> eventController,
                                           PropertyBindingController<Boolean> bindingController) {
        this(eventController, bindingController, false);
    }


    public SimpleObservableBooleanProperty(EventController eventController, boolean initialValue) {
        this(eventController, new AtomicPropertyBindingController<>(), initialValue);
    }

    public SimpleObservableBooleanProperty(EventController eventController) {
        this(eventController, false);
    }

    public SimpleObservableBooleanProperty(boolean initialValue) {
        this(Controllers.newSyncExecutionController(), initialValue);
    }

    public SimpleObservableBooleanProperty() {
        this(false);
    }

    @Override
    protected void setInternalDirect(Boolean value) {
        mValue = value;
    }

    @Override
    public void setInternal(boolean value) {
        if (mValue != value) {
            boolean oldValue = mValue;
            mValue = value;
            fireValueChangedEvent(oldValue, value);
        }
    }

    @Override
    public boolean getInternal() {
        return mValue;
    }
}
