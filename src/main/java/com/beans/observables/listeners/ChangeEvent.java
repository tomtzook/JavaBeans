package com.beans.observables.listeners;

import com.beans.observables.ObservableValue;
import com.notifier.Event;

public class ChangeEvent<T> implements Event {

    private final ObservableValue<? extends T> mObservableValue;
    private final T mOldValue;
    private final T mNewValue;

    public ChangeEvent(ObservableValue<? extends T> observableValue, T oldValue, T newValue) {
        mObservableValue = observableValue;
        mOldValue = oldValue;
        mNewValue = newValue;
    }

    public ObservableValue<? extends T> getObservableValue() {
        return mObservableValue;
    }

    public T getOldValue() {
        return mOldValue;
    }

    public T getNewValue() {
        return mNewValue;
    }
}
