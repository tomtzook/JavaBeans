package com.beans.observables.listeners;

import com.beans.observables.ObservableValue;
import com.notifier.Event;
import com.notifier.EventController;

import java.util.Arrays;
import java.util.function.Predicate;

public interface ObservableEventController<T> {

    void addListener(ChangeListener<? super T> listener);
    void removeListener(ChangeListener<? super T> listener);
    void fire(ChangeEvent<T> event);

    class Impl<T> implements ObservableEventController<T> {
        private final EventController mEventController;
        private final Predicate<Event> mEventPredicate;

        public Impl(EventController eventController, ObservableValue<T> thisObservable) {
            mEventController = eventController;
            mEventPredicate = new ListenerPredicate(thisObservable);
        }

        @SafeVarargs
        public Impl(EventController eventController, ObservableValue<T> thisObservable, ChangeListener<? super T>... listeners) {
            mEventController = eventController;
            mEventPredicate = new ListenerPredicate(thisObservable);
            Arrays.asList(listeners).forEach(this::addListener);
        }

        @Override
        public void addListener(ChangeListener<? super T> listener) {
            mEventController.registerListener(listener, mEventPredicate);
        }

        @Override
        public void removeListener(ChangeListener<? super T> listener) {
            mEventController.unregisterListener(listener);
        }

        @Override
        public void fire(ChangeEvent<T> event) {
            mEventController.fire(event, ChangeEvent.class, ChangeListener.class, ChangeListener::onChange);
        }
    }
}
