package com.beans.observables.binding;

public interface ObservableBinding<T> {

    void set(T value);
    T get();
}
