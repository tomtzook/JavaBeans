package com.beans.properties;

import com.beans.Property;

public abstract class PropertyBase<T> implements Property<T> {

    @Override
    public String toString() {
        return String.format("Property [value=%s]", String.valueOf(get()));
    }
}
