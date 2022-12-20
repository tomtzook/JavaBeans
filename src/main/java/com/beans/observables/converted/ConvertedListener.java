package com.beans.observables.converted;

import com.beans.observables.ObservableValue;
import com.beans.observables.listeners.ChangeEvent;
import com.beans.observables.listeners.ChangeListener;
import com.beans.util.function.OneWayConverter;

import java.lang.ref.WeakReference;

public class ConvertedListener<T, T2> implements ChangeListener<T> {

    private final WeakReference<ObservableValue<T2>> mValue;
    private final OneWayConverter<T, T2> mConverter;
    private final ChangeListener<? super T2> mListener;

    public ConvertedListener(ObservableValue<T2> value,
                             OneWayConverter<T, T2> converter,
                             ChangeListener<? super T2> listener) {
        mValue = new WeakReference<>(value);
        mConverter = converter;
        mListener = listener;
    }

    @Override
    public void onChange(ChangeEvent<T> event) {
        ObservableValue<T2> observableValue = mValue.get();
        if (observableValue == null) {
            throw new IllegalStateException("observable was garbage collected");
        }

        T2 oldValue = mConverter.convert(event.getOldValue());
        T2 newValue = mConverter.convert(event.getNewValue());
        mListener.onChange(new ChangeEvent<>(observableValue, oldValue, newValue));
    }
}
