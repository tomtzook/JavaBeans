package com.beans.observables;

import com.beans.observables.properties.ObservableBooleanProperty;
import com.beans.observables.properties.ObservableDoubleProperty;
import com.beans.observables.properties.ObservableIntProperty;
import com.beans.observables.properties.ObservableLongProperty;
import com.beans.observables.properties.ObservableProperty;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class PollingObservableFactory {

    private final ObservableFactory mFactory;
    private final Consumer<Runnable> mPoller;

    public PollingObservableFactory(ObservableFactory factory, Consumer<Runnable> poller) {
        mFactory = factory;
        mPoller = poller;
    }

    public PollingObservableFactory(ObservableFactory factory, ScheduledExecutorService executorService,
                                    long pollIntervalMs) {
        this(factory, (runnable)->
                executorService.scheduleAtFixedRate(runnable, pollIntervalMs, pollIntervalMs, TimeUnit.MILLISECONDS));
    }

    public PollingObservableFactory(ObservableFactory factory, ScheduledExecutorService executorService) {
        this(factory, executorService, 25);
    }

    public <T> ObservableValue<T> from(Supplier<T> supplier) {
        ObservableProperty<T> property = mFactory.newConcurrentProperty(supplier.get());
        mPoller.accept(()-> {
            property.set(supplier.get());
        });

        return property;
    }

    public ObservableBooleanValue from(BooleanSupplier supplier) {
        ObservableBooleanProperty property = mFactory.newConcurrentBooleanProperty(supplier.getAsBoolean());
        mPoller.accept(()-> {
            property.setAsBoolean(supplier.getAsBoolean());
        });

        return property;
    }

    public ObservableIntValue from(IntSupplier supplier) {
        ObservableIntProperty property = mFactory.newConcurrentIntProperty(supplier.getAsInt());
        mPoller.accept(()-> {
            property.setAsInt(supplier.getAsInt());
        });

        return property;
    }

    public ObservableDoubleValue from(DoubleSupplier supplier) {
        ObservableDoubleProperty property = mFactory.newConcurrentDoubleProperty(supplier.getAsDouble());
        mPoller.accept(()-> {
            property.setAsDouble(supplier.getAsDouble());
        });

        return property;
    }

    public ObservableLongValue from(LongSupplier supplier) {
        ObservableLongProperty property = mFactory.newConcurrentLongProperty(supplier.getAsLong());
        mPoller.accept(()-> {
            property.setAsLong(supplier.getAsLong());
        });

        return property;
    }
}
