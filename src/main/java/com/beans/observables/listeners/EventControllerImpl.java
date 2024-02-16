package com.beans.observables.listeners;

import com.beans.observables.ObservableValue;
import com.notifier.Event;
import com.notifier.EventController;
import com.notifier.RegisteredListener;

import java.util.Arrays;
import java.util.function.Predicate;

public class EventControllerImpl<T> implements ObservableEventController<T> {
    private final EventController mEventController;
    private final Predicate<Event> mEventPredicate;

    public EventControllerImpl(EventController eventController, ObservableValue<T> thisObservable) {
        mEventController = eventController;
        mEventPredicate = new ListenerPredicate(thisObservable);
    }

    @SafeVarargs
    public EventControllerImpl(EventController eventController, ObservableValue<T> thisObservable, ChangeListener<? super T>... listeners) {
        mEventController = eventController;
        mEventPredicate = new ListenerPredicate(thisObservable);
        Arrays.asList(listeners).forEach(this::addListener);
    }

    @Override
    public RegisteredListener addListener(ChangeListener<? super T> listener) {
        return mEventController.registerListener(listener, mEventPredicate);
    }

    @Override
    public void fire(ChangeEvent<T> event) {
        mEventController.fire(event, ChangeEvent.class, ChangeListener.class, ChangeListener::onChange);
    }
}
