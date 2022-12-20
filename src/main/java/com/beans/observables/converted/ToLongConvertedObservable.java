package com.beans.observables.converted;

import com.beans.observables.ObservableLongValue;
import com.beans.observables.ObservableValue;
import com.beans.util.function.OneWayConverter;
import com.beans.util.function.ToLongConverter;

public class ToLongConvertedObservable<T> extends ConvertedObservable<T, Long> implements ObservableLongValue {

    public ToLongConvertedObservable(ObservableValue<T> base, OneWayConverter<T, Long> converter) {
        super(base, converter);
    }

    @Override
    public long getAsLong() {
        Long value = get();
        return value == null ? 0 : value;
    }

    @Override
    public ObservableLongValue asLong(ToLongConverter<Long> converter) {
        throw new UnsupportedOperationException("current observable is already ObservableLongValue");
    }
}
