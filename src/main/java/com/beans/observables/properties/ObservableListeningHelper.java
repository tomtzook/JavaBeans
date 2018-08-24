package com.beans.observables.properties;

import com.beans.observables.ObservableValue;
import com.beans.observables.listeners.ChangeListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/*package*/ class ObservableListeningHelper<T> {

    public static <T> ObservableListeningHelper<T> createSimple(ObservableValue<T> observable) {
        return new ObservableListeningHelper<T>(observable, new ArrayList<ChangeListener<? super T>>(2));
    }

    public static <T> ObservableListeningHelper<T> createSynchronized(ObservableValue<T> observable) {
        return new ObservableListeningHelper<T>(observable, new CopyOnWriteArrayList<ChangeListener<? super T>>());
    }

    private final ObservableValue<T> mObservable;
    private final List<ChangeListener<? super T>> mChangeListeners;

    ObservableListeningHelper(ObservableValue<T> observable, List<ChangeListener<? super T>> changeListeners){
        mObservable = observable;

        mChangeListeners = changeListeners;
    }

    public void addListener(ChangeListener<? super T> listener) {
        mChangeListeners.add(listener);
    }

    public void removeListener(ChangeListener<? super T> listener) {
        mChangeListeners.remove(listener);
    }

    public void fireValueChangedEvent(final T oldValue, final T newValue) {
        final List<ChangeListener<? super T>> listeners = new ArrayList<ChangeListener<? super T>>(mChangeListeners);

        invokeListeners(listeners, oldValue, newValue);
    }

    void invokeListeners(List<ChangeListener<? super T>> listeners, T oldValue, T newValue) {
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
}
