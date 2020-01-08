package com.beans.observables.properties;

import com.beans.observables.listeners.ChangeListener;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ObservablePropertyTest {

    @Test
    public void set_fireEvents_listenersInvoked() {
        Object initialValue = new Object();
        Object newValue = new Object();

        ObservableProperty<Object> property = new SimpleObservableProperty<Object>(initialValue);

        ChangeListener changeListener = mock(ChangeListener.class);
        property.addChangeListener(changeListener);

        property.set(newValue);

        verify(changeListener, times(1)).changed(eq(property), eq(initialValue), eq(newValue));
    }
}