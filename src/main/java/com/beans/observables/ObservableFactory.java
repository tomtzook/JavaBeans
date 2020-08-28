package com.beans.observables;

import com.beans.observables.binding.AtomicPropertyBindingController;
import com.beans.observables.properties.ObservableBooleanProperty;
import com.beans.observables.properties.ObservableDoubleProperty;
import com.beans.observables.properties.ObservableIntProperty;
import com.beans.observables.properties.ObservableLongProperty;
import com.beans.observables.properties.ObservableProperty;
import com.beans.observables.properties.SimpleObservableBooleanProperty;
import com.beans.observables.properties.SimpleObservableDoubleProperty;
import com.beans.observables.properties.SimpleObservableIntProperty;
import com.beans.observables.properties.SimpleObservableLongProperty;
import com.beans.observables.properties.SimpleObservableProperty;
import com.beans.observables.properties.atomic.AtomicObservableBooleanProperty;
import com.beans.observables.properties.atomic.AtomicObservableDoubleProperty;
import com.beans.observables.properties.atomic.AtomicObservableIntProperty;
import com.beans.observables.properties.atomic.AtomicObservableLongProperty;
import com.beans.observables.properties.atomic.AtomicObservableProperty;
import com.notifier.Controllers;
import com.notifier.EventController;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class ObservableFactory {

    private final EventController mEventController;

    public ObservableFactory(EventController eventController) {
        mEventController = eventController;
    }

    /**
     * Creates a new factory whose properties will use the given executor for event
     * dispatching. i.e., listeners will be dispatching via the given executor.
     * If the executor is synchronized, than listeners will be dispatched from the
     * thread which changes the value ({@link ObservableProperty#set(Object)}).
     * <p>
     *     Equivalent to:
     * </p>
     * <pre>
     *     new ObservableFactory(Controllers.newExecutorBasedController(executor))
     * </pre>
     */
    public ObservableFactory(Executor executor) {
        this(Controllers.newExecutorBasedController(executor));
    }

    /**
     * Creates a new factory whose properties will use synchronized event
     * dispatching. i.e., listeners will be dispatching in the same thread
     * which changes the value ({@link ObservableProperty#set(Object)}).
     * <p>
     *     Equivalent to:
     * </p>
     * <pre>
     *     new ObservableFactory(Controllers.newSyncExecutionController())
     * </pre>
     */
    public ObservableFactory() {
        this(Controllers.newSyncExecutionController());
    }

    public ObservableFactory(ExecutorService executorService) {
        this(Controllers.newExecutorBasedController(executorService));
    }

    public <T> ObservableProperty<T> newProperty(T initialValue) {
        return new SimpleObservableProperty<T>(
                mEventController,
                new AtomicPropertyBindingController<>(),
                initialValue);
    }

    public <T> ObservableProperty<T> newConcurrentProperty(T initialValue) {
        return new AtomicObservableProperty<>(
                mEventController,
                new AtomicPropertyBindingController<>(),
                initialValue);
    }

    public ObservableBooleanProperty newBooleanProperty(boolean initialValue) {
        return new SimpleObservableBooleanProperty(
                mEventController,
                new AtomicPropertyBindingController<>(),
                initialValue);
    }

    public ObservableBooleanProperty newConcurrentBooleanProperty(boolean initialValue) {
        return new AtomicObservableBooleanProperty(
                mEventController,
                new AtomicPropertyBindingController<>(),
                initialValue);
    }

    public ObservableIntProperty newIntProperty(int initialValue) {
        return new SimpleObservableIntProperty(
                mEventController,
                new AtomicPropertyBindingController<>(),
                initialValue);
    }

    public ObservableIntProperty newConcurrentIntProperty(int initialValue) {
        return new AtomicObservableIntProperty(
                mEventController,
                new AtomicPropertyBindingController<>(),
                initialValue);
    }

    public ObservableDoubleProperty newDoubleProperty(double initialValue) {
        return new SimpleObservableDoubleProperty(
                mEventController,
                new AtomicPropertyBindingController<>(),
                initialValue);
    }

    public ObservableDoubleProperty newConcurrentDoubleProperty(double initialValue) {
        return new AtomicObservableDoubleProperty(
                mEventController,
                new AtomicPropertyBindingController<>(),
                initialValue);
    }

    public ObservableLongProperty newLongProperty(long initialValue) {
        return new SimpleObservableLongProperty(
                mEventController,
                new AtomicPropertyBindingController<>(),
                initialValue);
    }

    public ObservableLongProperty newConcurrentLongProperty(long initialValue) {
        return new AtomicObservableLongProperty(
                mEventController,
                new AtomicPropertyBindingController<>(),
                initialValue);
    }
}
