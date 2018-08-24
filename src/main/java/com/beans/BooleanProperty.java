package com.beans;

import java.util.function.BooleanSupplier;

public interface BooleanProperty extends BooleanSupplier, Property<Boolean> {

    void setAsBoolean(boolean value);
}
