package com.beans.observables.properties;

import com.beans.observables.ObservableValue;
import com.beans.observables.listeners.ChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class ObservableListeningHelper<T> {

    public static <T> ObservableListeningHelper<T> createSimple(ObservableValue<T> observable, T currentValue) {
        return new Simple<T>(observable, currentValue);
    }

    public static <T> ObservableListeningHelper<T> createSynchronized(ObservableValue<T> observable, T currentValue) {
        return new Synchronized<T>(observable, currentValue);
    }

    protected final ObservableValue<T> mObservable;
    protected final List<ChangeListener<? super T>> mChangeListeners;
    protected T mCurrentValue;

    protected ObservableListeningHelper(ObservableValue<T> observable, T initialValue, List<ChangeListener<? super T>> changeListeners){
        mObservable = observable;

        mChangeListeners = changeListeners;
        mCurrentValue = initialValue;
    }

    public void addListener(ChangeListener<? super T> listener) {
        mChangeListeners.add(listener);
    }

    public void removeListener(ChangeListener<? super T> listener) {
        mChangeListeners.remove(listener);
    }

    protected void invokeListeners(List<ChangeListener<? super T>> listeners, T oldValue, T newValue) {
        if (listeners.size() > 0 &&
                (newValue == null ? (oldValue != null) : !newValue.equals(oldValue))) {
            for (ChangeListener<? super T> changeListener : listeners) {
                try {
                    changeListener.changed(mObservable, oldValue, newValue);
                } catch (Throwable t) {
                    Thread.currentThread().getUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), t);
                }
            }
        }
    }

    public abstract void fireValueChangedEvent(T newValue);


    private static class Simple<T> extends ObservableListeningHelper<T> {

        Simple(ObservableValue<T> observable, T initialValue) {
            super(observable, initialValue, new ArrayList<ChangeListener<? super T>>(2));
        }

        @Override
        public void fireValueChangedEvent(T newValue) {
            final List<ChangeListener<? super T>> listeners = new ArrayList<ChangeListener<? super T>>(mChangeListeners);
            final T oldValue = mCurrentValue;
            mCurrentValue = newValue;

            invokeListeners(listeners, oldValue, newValue);
        }
    }

    private static class Synchronized<T> extends ObservableListeningHelper<T> {

        Synchronized(ObservableValue<T> observable, T initialValue) {
            super(observable, initialValue, new CopyOnWriteArrayList<ChangeListener<? super T>>());
        }

        @Override
        public synchronized void fireValueChangedEvent(final T newValue) {
            final List<ChangeListener<? super T>> listeners = new ArrayList<ChangeListener<? super T>>(mChangeListeners);
            final T oldValue = mCurrentValue;
            mCurrentValue = newValue;

            invokeListeners(listeners, oldValue, newValue);
        }
    }
}
