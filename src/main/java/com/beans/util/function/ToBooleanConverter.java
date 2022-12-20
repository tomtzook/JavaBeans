package com.beans.util.function;

@FunctionalInterface
public interface ToBooleanConverter<T> extends OneWayConverter<T, Boolean> {

    boolean convertToBoolean(T t);

    @Override
    default Boolean convert(T t) {
        return convertToBoolean(t);
    }
}
