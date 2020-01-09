package com.beans.observables.binding;

import com.beans.observables.ObservableValue;
import com.beans.observables.properties.ObservableProperty;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicPropertyBindingController<T> implements PropertyBindingController<T> {

    private final AtomicReference<ObservableBinding<T>> mBinding;

    public AtomicPropertyBindingController() {
        mBinding = new AtomicReference<>(null);
    }

    @Override
    public boolean isBound() {
        return mBinding.get() != null;
    }

    @Override
    public void bind(ObservableValue<T> observableValue) {
        if (!mBinding.compareAndSet(null, new SingleDirectionBinding<>(observableValue))) {
            throw new IllegalStateException("already bound");
        }
    }

    @Override
    public void bindBidirectional(ObservableProperty<T> observableProperty) {
        if (!mBinding.compareAndSet(null, new BiDirectionalBinding<>(observableProperty))) {
            throw new IllegalStateException("already bound");
        }
    }

    @Override
    public void unbind() {
        mBinding.set(null);
    }

    @Override
    public Optional<ObservableBinding<T>> getBinding() {
        return Optional.ofNullable(mBinding.get());
    }
}
