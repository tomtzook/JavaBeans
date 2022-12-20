package com.beans.observables.listeners;

import com.beans.observables.RegisteredListener;

public interface ObservableEventController<T> {

    RegisteredListener addListener(ChangeListener<? super T> listener);
    void fire(ChangeEvent<T> event);
}
