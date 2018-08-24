package com.beans;

import java.util.function.LongSupplier;

public interface LongProperty extends LongSupplier, Property<Long> {

    void setAsLong(long value);
}
