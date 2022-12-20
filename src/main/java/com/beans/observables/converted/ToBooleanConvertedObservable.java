package com.beans.observables.converted;

import com.beans.observables.ObservableBooleanValue;
import com.beans.observables.ObservableValue;
import com.beans.util.function.OneWayConverter;
import com.beans.util.function.ToBooleanConverter;

public class ToBooleanConvertedObservable<T> extends ConvertedObservable<T, Boolean> implements ObservableBooleanValue {

    public ToBooleanConvertedObservable(ObservableValue<T> base, OneWayConverter<T, Boolean> converter) {
        super(base, converter);
    }

    @Override
    public boolean getAsBoolean() {
        Boolean value = get();
        return value != null && value;
    }

    @Override
    public ObservableBooleanValue asBoolean(ToBooleanConverter<Boolean> converter) {
        throw new UnsupportedOperationException("current observable is already ObservableBooleanValue");
    }
}
