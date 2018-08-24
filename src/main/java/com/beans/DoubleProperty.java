package com.beans;

import java.util.function.DoubleSupplier;

public interface DoubleProperty extends DoubleSupplier {

    void setAsDouble(double value);
}
