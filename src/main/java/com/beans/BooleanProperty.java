package com.beans;

import java.util.function.BooleanSupplier;

public interface BooleanProperty extends BooleanSupplier {

    void setAsBoolean(boolean value);
}
