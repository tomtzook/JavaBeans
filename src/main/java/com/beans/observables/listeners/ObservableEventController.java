package com.beans.observables.listeners;

import com.notifier.EventController;

import java.util.Arrays;

public interface ObservableEventController<T> {

    void addListener(ChangeListener<? super T> listener);
    void removeListener(ChangeListener<? super T> listener);
    void fire(ChangeEvent<T> event);

    class Impl<T> implements ObservableEventController<T> {
        private final EventController mEventController;

        public Impl(EventController eventController) {
            mEventController = eventController;
        }

        @SafeVarargs
        public Impl(EventController eventController, ChangeListener<? super T>... listeners) {
            mEventController = eventController;
            Arrays.asList(listeners).forEach(this::addListener);
        }

        @Override
        public void addListener(ChangeListener<? super T> listener) {
            mEventController.registerListener(listener);
        }

        @Override
        public void removeListener(ChangeListener<? super T> listener) {
            throw new UnsupportedOperationException("not supported yet");
        }

        @Override
        public void fire(ChangeEvent<T> event) {
            mEventController.fire(event, ChangeEvent.class, ChangeListener.class, ChangeListener::onChange);
        }
    }
}
