package com.beans.observables.binding;

import com.beans.observables.ObservableValue;
import com.beans.observables.listeners.ChangeEvent;
import com.beans.observables.properties.ObservableProperty;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

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
    public void bind(ObservableValue<T> observableValue, Consumer<ChangeEvent<T>> onObservableValueChange) {
        if (!mBinding.compareAndSet(null, new SingleDirectionBinding<>(observableValue, onObservableValueChange))) {
            throw new IllegalStateException("already bound");
        }
    }

    @Override
    public void bindBidirectional(ObservableProperty<T> observableProperty, Consumer<ChangeEvent<T>> onObservableValueChange) {
        if (!mBinding.compareAndSet(null, new BiDirectionalBinding<>(observableProperty, onObservableValueChange))) {
            throw new IllegalStateException("already bound");
        }
    }

    @Override
    public Optional<ObservableBinding<T>> unbind() {
        ObservableBinding<T> binding = mBinding.getAndSet(null);
        if (binding != null) {
            binding.onUnbind();
        }

        return Optional.ofNullable(binding);
    }

    @Override
    public Optional<ObservableBinding<T>> getBinding() {
        return Optional.ofNullable(mBinding.get());
    }
}
