package com.beans;

import java.util.function.IntSupplier;

public interface IntProperty extends IntSupplier, Property<Integer> {

    void setAsInt(int value);
}
