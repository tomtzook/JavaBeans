package com.beans.util.function;

@FunctionalInterface
public interface ToDoubleConverter<T> extends OneWayConverter<T, Double> {

    double convertToDouble(T t);

    @Override
    default Double convert(T t) {
        return convertToDouble(t);
    }
}
