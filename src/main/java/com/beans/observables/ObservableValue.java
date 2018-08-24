package com.beans.observables;

import com.beans.observables.listeners.ChangeListener;

import java.util.function.Supplier;

public interface ObservableValue<T> extends Supplier<T> {

    void addChangeListener(ChangeListener<? super T> changeListener);
    void removeChangeListener(ChangeListener<? super T> changeListener);
}
