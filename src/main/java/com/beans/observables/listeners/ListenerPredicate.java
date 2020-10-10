package com.beans.observables.listeners;

import com.beans.observables.ObservableValue;
import com.notifier.Event;

import java.lang.ref.WeakReference;
import java.util.function.Predicate;

public class ListenerPredicate implements Predicate<Event> {

    private final WeakReference<ObservableValue<?>> mObservable;

    public ListenerPredicate(ObservableValue<?> observable) {
        mObservable = new WeakReference<>(observable);
    }

    @Override
    public boolean test(Event event) {
        ObservableValue<?> observable = mObservable.get();

        if (observable == null) {
            return true;
        }

        return event instanceof ChangeEvent &&
                ((ChangeEvent)event).getObservableValue().equals(observable);
    }
}
