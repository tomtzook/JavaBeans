package com.beans.observables.properties;

import com.beans.observables.ObservableValue;
import com.beans.observables.binding.AtomicPropertyBindingController;
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
import com.notifier.RegisteredListener;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ObservablePropertyImplTest {

    @ParameterizedTest(name = "{0}.set({1})")
    @MethodSource("newValueWithListenerArguments")
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
    @MethodSource("sameValueWithListenerArguments")
    public void set_sameValue_doesNotCallListener(ObservableProperty property, Object value, ChangeListener listener) throws Exception {
        property.set(value);

        verify(listener, never()).onChange(any(ChangeEvent.class));
    }

    @ParameterizedTest(name = "{0}.bind(...).get()")
    @MethodSource("sameValueWithObservable")
    public void bindAndGet_withObservable_getsValueFromBound(ObservableProperty property, Object currentValue, Object value, ObservableValue observableValue) throws Exception {
        property.bind(observableValue);

        Object getValue = property.get();

        assertThat(getValue, equalTo(value));
        assertThat(getValue, not(equalTo(currentValue)));
        verify(observableValue, times(1)).get();
    }

    @ParameterizedTest(name = "{0}.bind(...).set(...)")
    @MethodSource("sameValueWithObservable")
    public void bindAndSet_withObservable_throwsIllegalStateException(ObservableProperty property, Object currentValue, Object value, ObservableValue observableValue) throws Exception {
        assertThrows(IllegalStateException.class, ()-> {
            property.bind(observableValue);
            property.set(value);
        });
    }

    @ParameterizedTest(name = "{0}.bindBidirectional(...).get()")
    @MethodSource("sameValueWithProperty")
    public void bindBidirectionalAndGet_withObservable_getsValueFromBound(ObservableProperty property, Object currentValue, Object value, ObservableProperty observableProperty) throws Exception {
        property.bindBidirectional(observableProperty);

        Object getValue = property.get();

        assertThat(getValue, equalTo(value));
        assertThat(getValue, not(equalTo(currentValue)));
        verify(observableProperty, times(1)).get();
    }

    @ParameterizedTest(name = "{0}.bindBidirectional(...).get()")
    @MethodSource("listenerUpdateInBind")
    public void bindBidirectionalAndSet_withObservableAndListener_callsListener(ObservableProperty property, Object currentValue, Object value, ObservableProperty observableProperty, ChangeListener listener) throws Exception {
        property.bindBidirectional(observableProperty);
        observableProperty.set(currentValue);

        ArgumentCaptor<ChangeEvent> captor = ArgumentCaptor.forClass(ChangeEvent.class);
        verify(listener, times(1)).onChange(captor.capture());

        ChangeEvent event = captor.getValue();
        assertThat(event.getObservableValue(), equalTo(property));
        assertThat(event.getNewValue(), equalTo(currentValue));
        assertThat(event.getOldValue(), not(equalTo(currentValue)));
    }

    @ParameterizedTest(name = "{0}.bindBidirectional(...).set(...)")
    @MethodSource("sameValueWithProperty")
    public void bindBidirectionalAndSet_withObservable_setsValue(ObservableProperty property, Object currentValue, Object value, ObservableProperty observableProperty) throws Exception {
        property.bindBidirectional(observableProperty);

        property.set(value);
        verify(observableProperty, times(1)).set(eq(value));
    }

    @ParameterizedTest(name = "{0}.bindBidirectional(...).unbind().get()")
    @MethodSource("sameValueWithProperty")
    public void bindBidirectionalUnbindGet_withObservable_valueUpdatedAfterUnbind(ObservableProperty property, Object currentValue, Object value, ObservableProperty observableProperty) throws Exception {
        property.bindBidirectional(observableProperty);
        property.unbind();

        Object getValue = property.get();

        assertThat(getValue, equalTo(value));
        assertThat(getValue, not(equalTo(currentValue)));
    }

    public static Stream<Arguments> newValueWithListenerArguments() {
        return implementations().stream()
                .map((func) -> {
                    ChangeListener listener = mock(ChangeListener.class);
                    Impl impl = func.apply(listener);
                    return Arguments.of(impl.mProperty, valueForProperty(impl), listener);
                });
    }

    public static Stream<Arguments> sameValueWithListenerArguments() {
        return implementations().stream()
                .map((func) -> {
                    ChangeListener listener = mock(ChangeListener.class);
                    Impl impl = func.apply(listener);
                    return Arguments.of(impl.mProperty, impl.mProperty.get(), listener);
                });
    }

    public static Stream<Arguments> sameValueWithObservable() {
        return implementations().stream()
                .map((func) -> {
                    Impl impl = func.apply(mock(ChangeListener.class));

                    Object someValue = valueForProperty(impl);
                    ObservableValue value = mock(ObservableValue.class);
                    when(value.get()).thenReturn(someValue);
                    when(value.addChangeListener(any())).thenReturn(mock(RegisteredListener.class));

                    return Arguments.of(impl.mProperty, impl.mProperty.get(), someValue, value);
                });
    }

    public static Stream<Arguments> sameValueWithProperty() {
        return implementations().stream()
                .map((func) -> {
                    Impl impl = func.apply(mock(ChangeListener.class));

                    Object someValue = valueForProperty(impl);
                    ObservableProperty value = mock(ObservableProperty.class);
                    when(value.get()).thenReturn(someValue);
                    when(value.addChangeListener(any())).thenReturn(mock(RegisteredListener.class));

                    return Arguments.of(impl.mProperty, impl.mProperty.get(), someValue, value);
                });
    }

    public static Stream<Arguments> listenerUpdateInBind() {
        return implementations().stream()
                .map((func) -> {
                    ChangeListener listener = mock(ChangeListener.class);
                    Impl impl = func.apply(listener);

                    Object someValue = valueForProperty(impl);
                    AtomicReference<ChangeListener> setListener = new AtomicReference<>();
                    ObservableProperty value = mock(ObservableProperty.class);
                    doAnswer(invocation -> {
                        setListener.set(invocation.getArgument(0));
                        return null;
                    }).when(value).addChangeListener(any(ChangeListener.class));
                    when(value.get()).thenReturn(someValue);
                    doAnswer(invocation -> {
                        ChangeListener l = setListener.get();
                        if (l != null) {
                            l.onChange(new ChangeEvent(value, someValue, invocation.getArgument(0)));
                        }
                        return null;
                    }).when(value).set(any());

                    return Arguments.of(impl.mProperty, impl.mProperty.get(), someValue, value, listener);
                });
    }

    @SuppressWarnings("unchecked")
    private static List<Function<ChangeListener, Impl>> implementations() {
        EventController eventController = Controllers.newSyncExecutionController();
        return Arrays.asList(
                (l)-> new Impl(new SimpleObservableProperty<>(eventController, null),
                        Object.class, l),
                (l)-> new Impl(new SimpleObservableBooleanProperty(eventController, false),
                        Boolean.class, l),
                (l)-> new Impl(new SimpleObservableDoubleProperty(eventController, 0.0),
                        Double.class, l),
                (l)-> new Impl(new SimpleObservableIntProperty(eventController, 0),
                        Integer.class, l),
                (l)-> new Impl(new SimpleObservableLongProperty(eventController, 0),
                        Long.class, l),
                (l)-> new Impl(new AtomicObservableProperty(eventController, null),
                        Object.class, l),
                (l)-> new Impl(new AtomicObservableBooleanProperty(eventController, false),
                        Boolean.class, l),
                (l)-> new Impl(new AtomicObservableDoubleProperty(eventController, 0.0),
                        Double.class, l),
                (l)-> new Impl(new AtomicObservableIntProperty(eventController, 0),
                        Integer.class, l),
                (l)-> new Impl(new AtomicObservableLongProperty(eventController, 0),
                        Long.class, l)
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


        Impl(ObservableProperty property, Class<?> innerType, ChangeListener listener) {
            mProperty = property;
            mInnerType = innerType;
            property.addChangeListener(listener);
        }
    }
}
