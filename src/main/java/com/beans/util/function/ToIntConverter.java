package com.beans.util.function;

@FunctionalInterface
public interface ToIntConverter<T> extends OneWayConverter<T, Integer> {

    int convertToInt(T t);

    @Override
    default Integer convert(T t) {
        return convertToInt(t);
    }
}
