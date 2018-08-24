package com.beans.observables.properties;

import com.beans.observables.listeners.ChangeListener;

public abstract class ObservablePropertyBase<T> implements ObservableProperty<T> {

    private ObservableListeningHelper<T> mObservableListeningHelper;

    public ObservablePropertyBase(T initialValue, boolean threadSafe) {
        mObservableListeningHelper = threadSafe
                ? ObservableListeningHelper.createSynchronized(this, initialValue)
                : ObservableListeningHelper.createSimple(this, initialValue);
    }

    @Override
    public final void addChangeListener(ChangeListener<? super T> changeListener) {
        mObservableListeningHelper.addListener(changeListener);
    }

    @Override
    public final void removeChangeListener(ChangeListener<? super T> changeListener) {
        mObservableListeningHelper.removeListener(changeListener);
    }

    protected final void fireValueChangedEvent(T newValue) {
        mObservableListeningHelper.fireValueChangedEvent(newValue);
    }

    @Override
    public String toString() {
        return String.format("ObservableProperty [value=%s]", String.valueOf(get()));
    }
}
