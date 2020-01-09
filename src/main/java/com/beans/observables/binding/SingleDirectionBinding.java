package com.beans.observables.binding;

import com.beans.observables.ObservableValue;

public class SingleDirectionBinding<T> implements ObservableBinding<T> {

    private final ObservableValue<T> mBound;

    public SingleDirectionBinding(ObservableValue<T> bound) {
        mBound = bound;
    }

    @Override
    public void set(T value) {
        throw new IllegalStateException("Cannot set for single-direction bind");
    }

    @Override
    public T get() {
        return mBound.get();
    }
}
