package com.beans;

import com.beans.properties.SimpleBooleanProperty;
import com.beans.properties.SimpleDoubleProperty;
import com.beans.properties.SimpleIntProperty;
import com.beans.properties.SimpleLongProperty;
import com.beans.properties.SimpleProperty;

public class Properties {

    private Properties() {}

    public static <T> Property<T> of(T value) {
        return new SimpleProperty<>(value);
    }

    public static IntProperty of(int value) {
        return new SimpleIntProperty(value);
    }

    public static DoubleProperty of(double value) {
        return new SimpleDoubleProperty(value);
    }

    public static BooleanProperty of(boolean value) {
        return new SimpleBooleanProperty(value);
    }

    public static LongProperty of(long value) {
        return new SimpleLongProperty(value);
    }
}
