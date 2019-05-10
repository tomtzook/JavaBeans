package com.beans.util.function;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;

public class Suppliers {

    private Suppliers() {}

    public static IntSupplier of(int value) {
        return ()->value;
    }

    public static DoubleSupplier of(double value) {
        return ()->value;
    }

    public static BooleanSupplier of(boolean value) {
        return ()->value;
    }

    public static LongSupplier of(long value) {
        return ()->value;
    }
}
