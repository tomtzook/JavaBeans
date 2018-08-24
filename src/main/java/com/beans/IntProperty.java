package com.beans;

import java.util.function.IntSupplier;

public interface IntProperty extends IntSupplier {

    void setAsInt(int value);
}
