package com.beans.util.function;

@FunctionalInterface
public interface ToLongConverter<T> extends OneWayConverter<T, Long> {

    long convertAsLong(T t);

    @Override
    default Long convert(T t) {
        return convertAsLong(t);
    }
}
