package com.beans.observables.listeners;

import com.notifier.RegisteredListener;

public interface ObservableEventController<T> {

    RegisteredListener addListener(ChangeListener<? super T> listener);
    void fire(ChangeEvent<T> event);
}
