package com.beans.observables.listeners;

import com.beans.observables.ObservableValue;
import com.notifier.Event;

import java.util.function.Predicate;

public class ListenerPredicate implements Predicate<Event> {

    private final ObservableValue<?> mObservable;

    public ListenerPredicate(ObservableValue<?> observable) {
        mObservable = observable;
    }

    @Override
    public boolean test(Event event) {
        if (mObservable == null) {
            return true;
        }

        return event instanceof ChangeEvent &&
                ((ChangeEvent)event).getObservableValue().equals(mObservable);
    }
}
