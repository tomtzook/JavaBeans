package com.beans.util.function;

@FunctionalInterface
public interface OneWayConverter<T, R> {

    R convert(T t);
}
