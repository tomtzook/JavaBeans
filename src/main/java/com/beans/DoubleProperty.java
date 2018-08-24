package com.beans;

import java.util.function.DoubleSupplier;

public interface DoubleProperty extends DoubleSupplier, Property<Double> {

    void setAsDouble(double value);
}
