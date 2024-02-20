package com.cuboiddroid.cuboidmod.util;

import java.io.Serializable;
import java.util.Objects;

public class Pair<K,V> implements Serializable {
    private K key;
    public K getKey() { return key; }

    private V value;
    public V getValue() { return value; }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;
        if (!Objects.equals(key, pair.key)
                || !Objects.equals(value, pair.value))
            return false;
        return true;
    }
}

