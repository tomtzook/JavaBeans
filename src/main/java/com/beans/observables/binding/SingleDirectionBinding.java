package com.beans.observables.binding;

import com.beans.observables.ObservableValue;
import com.beans.observables.listeners.ChangeEvent;
import com.beans.observables.listeners.ChangeListener;

import java.util.function.Consumer;

public class SingleDirectionBinding<T> implements ObservableBinding<T> {

    private final ObservableValue<T> mBound;
    private final ChangeListener<T> mListener;

    public SingleDirectionBinding(ObservableValue<T> bound, Consumer<ChangeEvent<T>> onObservableValueChange) {
        mBound = bound;
        mListener = onObservableValueChange::accept;
        mBound.addChangeListener(mListener);
    }

    @Override
    public void set(T value) {
        throw new IllegalStateException("Cannot set for single-direction bind");
    }

    @Override
    public T get() {
        return mBound.get();
    }

    @Override
    public void onUnbind() {
        mBound.removeChangeListener(mListener);
    }
}
