package com.beans.observables.converted;

import com.beans.observables.ObservableDoubleValue;
import com.beans.observables.ObservableValue;
import com.beans.util.function.OneWayConverter;
import com.beans.util.function.ToDoubleConverter;

public class ToDoubleConvertedObservable<T> extends ConvertedObservable<T, Double> implements ObservableDoubleValue {

    public ToDoubleConvertedObservable(ObservableValue<T> base, OneWayConverter<T, Double> converter) {
        super(base, converter);
    }

    @Override
    public double getAsDouble() {
        Double value = get();
        return value == null ? 0 : value;
    }

    @Override
    public ObservableDoubleValue asDouble(ToDoubleConverter<Double> converter) {
        throw new UnsupportedOperationException("current observable is already ObservableDoubleValue");
    }
}
