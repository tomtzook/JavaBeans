package com.beans.observables.converted;

import com.beans.observables.ObservableBooleanValue;
import com.beans.observables.ObservableDoubleValue;
import com.beans.observables.ObservableIntValue;
import com.beans.observables.ObservableLongValue;
import com.beans.observables.ObservableValue;
import com.beans.observables.RegisteredListener;
import com.beans.observables.listeners.ChangeListener;
import com.beans.util.function.OneWayConverter;
import com.beans.util.function.ToBooleanConverter;
import com.beans.util.function.ToDoubleConverter;
import com.beans.util.function.ToIntConverter;
import com.beans.util.function.ToLongConverter;

public class ConvertedObservable<T, T2> implements ObservableValue<T2> {

    private final ObservableValue<T> mBase;
    private final OneWayConverter<T, T2> mConverter;

    public ConvertedObservable(ObservableValue<T> base, OneWayConverter<T, T2> converter) {
        mBase = base;
        mConverter = converter;
    }

    @Override
    public Object getBean() {
        return mBase.getBean();
    }

    @Override
    public RegisteredListener addChangeListener(ChangeListener<? super T2> changeListener) {
        return mBase.addChangeListener(new ConvertedListener<>(this, mConverter, changeListener));
    }

    @Override
    public void bind(ObservableValue<T2> observableValue) {
        throw new UnsupportedOperationException("binding not supported for converted observables");
    }

    @Override
    public void unbind() {

    }

    @Override
    public <T21> ObservableValue<T21> as(OneWayConverter<T2, T21> converter) {
        return new ConvertedObservable<>(this, converter);
    }

    @Override
    public ObservableBooleanValue asBoolean(ToBooleanConverter<T2> converter) {
        return new ToBooleanConvertedObservable<>(this, converter);
    }

    @Override
    public ObservableIntValue asInt(ToIntConverter<T2> converter) {
        return new ToIntConvertedObservable<>(this, converter);
    }

    @Override
    public ObservableLongValue asLong(ToLongConverter<T2> converter) {
        return new ToLongConvertedObservable<>(this, converter);
    }

    @Override
    public ObservableDoubleValue asDouble(ToDoubleConverter<T2> converter) {
        return new ToDoubleConvertedObservable<>(this, converter);
    }

    @Override
    public T2 get() {
        return mConverter.convert(mBase.get());
    }
}
