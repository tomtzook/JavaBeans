package com.beans;

import java.util.function.LongSupplier;

public interface LongProperty extends LongSupplier {

    void setAsLong(long value);
}
