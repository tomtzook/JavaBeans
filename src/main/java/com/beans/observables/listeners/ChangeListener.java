package com.beans.observables.listeners;

import com.beans.observables.ObservableValue;

public interface ChangeListener<T> {

    void changed(ObservableValue<? extends T> observable, T oldValue, T newValue);
}
