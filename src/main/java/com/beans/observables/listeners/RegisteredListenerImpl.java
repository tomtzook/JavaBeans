package com.beans.observables.listeners;

import com.beans.observables.RegisteredListener;
import com.notifier.EventController;
import com.notifier.Listener;

public class RegisteredListenerImpl implements RegisteredListener {

    private final EventController mEventController;
    private final Listener mListener;

    public RegisteredListenerImpl(EventController eventController, Listener listener) {
        mEventController = eventController;
        mListener = listener;
    }

    @Override
    public void remove() {
        mEventController.unregisterListener(mListener);
    }
}
