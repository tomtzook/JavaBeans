package com.beans;

import java.util.function.Supplier;

public interface Property<T> extends Supplier<T> {

    void set(T value);
}
