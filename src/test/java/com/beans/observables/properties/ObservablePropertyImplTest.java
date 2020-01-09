package com.beans.observables.properties;

import com.beans.observables.listeners.ChangeEvent;
import com.beans.observables.listeners.ChangeListener;
import com.beans.observables.listeners.ObservableEventController;
import com.beans.observables.properties.atomic.AtomicObservableBooleanProperty;
import com.beans.observables.properties.atomic.AtomicObservableDoubleProperty;
import com.beans.observables.properties.atomic.AtomicObservableIntProperty;
import com.beans.observables.properties.atomic.AtomicObservableLongProperty;
import com.beans.observables.properties.atomic.AtomicObservableProperty;
import com.notifier.Controllers;
import com.notifier.EventController;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ObservablePropertyImplTest {

    @ParameterizedTest(name = "{0}.set({1})")
    @MethodSource("newValueArguments")
    public void set_newValue_callsListener(ObservableProperty property, Object newValue, ChangeListener listener) throws Exception {
        property.set(newValue);

        ArgumentCaptor<ChangeEvent> captor = ArgumentCaptor.forClass(ChangeEvent.class);
        verify(listener, times(1)).onChange(captor.capture());

        ChangeEvent event = captor.getValue();
        assertThat(event.getObservableValue(), equalTo(property));
        assertThat(event.getNewValue(), equalTo(newValue));
        assertThat(event.getOldValue(), not(equalTo(newValue)));
    }

    @ParameterizedTest(name = "{0}.set({1})")
    @MethodSource("sameValueArguments")
    public void set_sameValue_doesNotCallListener(ObservableProperty property, Object newValue, ChangeListener listener) throws Exception {
        property.set(newValue);

        verify(listener, never()).onChange(any(ChangeEvent.class));
    }


    public static Stream<Arguments> newValueArguments() {
        return implementations().stream()
                .map((func) -> {
                    ChangeListener listener = mock(ChangeListener.class);
                    Impl impl = func.apply(listener);
                    return Arguments.of(impl.mProperty, valueForProperty(impl), listener);
                });
    }

    public static Stream<Arguments> sameValueArguments() {
        return implementations().stream()
                .map((func) -> {
                    ChangeListener listener = mock(ChangeListener.class);
                    Impl impl = func.apply(listener);
                    return Arguments.of(impl.mProperty, impl.mProperty.get(), listener);
                });
    }

    private static List<Function<ChangeListener, Impl>> implementations() {
        EventController eventController = Controllers.newSyncExecutionController();
        return Arrays.asList(
                (l)-> new Impl(new SimpleObservableProperty<>(new ObservableEventController.Impl<>(eventController, l)), Object.class),
                (l)-> new Impl(new SimpleObservableBooleanProperty(new ObservableEventController.Impl<>(eventController, l)), Boolean.class),
                (l)-> new Impl(new SimpleObservableDoubleProperty(new ObservableEventController.Impl<>(eventController, l)), Double.class),
                (l)-> new Impl(new SimpleObservableIntProperty(new ObservableEventController.Impl<>(eventController, l)), Integer.class),
                (l)-> new Impl(new SimpleObservableLongProperty(new ObservableEventController.Impl<>(eventController, l)), Long.class),
                (l)-> new Impl(new AtomicObservableProperty(new ObservableEventController.Impl<>(eventController, l)), Object.class),
                (l)-> new Impl(new AtomicObservableBooleanProperty(new ObservableEventController.Impl<>(eventController, l)), Boolean.class),
                (l)-> new Impl(new AtomicObservableDoubleProperty(new ObservableEventController.Impl<>(eventController, l)), Double.class),
                (l)-> new Impl(new AtomicObservableIntProperty(new ObservableEventController.Impl<>(eventController, l)), Integer.class),
                (l)-> new Impl(new AtomicObservableLongProperty(new ObservableEventController.Impl<>(eventController, l)), Long.class)
        );
    }

    private static Object valueForProperty(Impl impl) {
        Map<Class<?>, Object> valuesMap = new HashMap<>();
        valuesMap.put(Double.class, 1000.0);
        valuesMap.put(Boolean.class, true);
        valuesMap.put(Integer.class, 43);
        valuesMap.put(Long.class, 10000L);

        if (valuesMap.containsKey(impl.mInnerType)) {
            return valuesMap.get(impl.mInnerType);
        }

        return new Object();
    }

    private static class Impl {
        final ObservableProperty mProperty;
        final Class<?> mInnerType;


        Impl(ObservableProperty property, Class<?> innerType) {
            mProperty = property;
            mInnerType = innerType;
        }
    }
}
