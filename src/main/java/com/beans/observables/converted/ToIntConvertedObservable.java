package com.beans.observables.converted;

import com.beans.observables.ObservableIntValue;
import com.beans.observables.ObservableValue;
import com.beans.util.function.OneWayConverter;
import com.beans.util.function.ToIntConverter;

public class ToIntConvertedObservable<T> extends ConvertedObservable<T, Integer> implements ObservableIntValue {

    public ToIntConvertedObservable(ObservableValue<T> base, OneWayConverter<T, Integer> converter) {
        super(base, converter);
    }

    @Override
    public int getAsInt() {
        Integer value = get();
        return value == null ? 0 : value;
    }

    @Override
    public ObservableIntValue asInt(ToIntConverter<Integer> converter) {
        throw new UnsupportedOperationException("current observable is already ObservableIntValue");
    }
}
