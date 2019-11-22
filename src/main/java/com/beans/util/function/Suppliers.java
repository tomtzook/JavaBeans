package com.beans.util.function;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class Suppliers {

    private Suppliers() {}

    public static <T> Supplier<T> of(T value) {
        return ()->value;
    }

    public static IntSupplier of(int value) {
        return ()->value;
    }

    public static DoubleSupplier of(double value) {
        return ()->value;
    }

    public static BooleanSupplier of(boolean value) {
        return ()->value;
    }

    public static BooleanSupplier not(BooleanSupplier supplier) {
        return ()->!supplier.getAsBoolean();
    }

    public static LongSupplier of(long value) {
        return ()->value;
    }
}
