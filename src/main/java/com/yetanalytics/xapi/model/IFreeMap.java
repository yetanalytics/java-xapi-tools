package com.yetanalytics.xapi.model;

import java.util.Map;
import java.util.Set;

/**
 * Interface for all freeform maps (Extensions and LangMaps) in xAPI Statements.
 */
public interface IFreeMap<K, V> {
    /**
     * Sets an entry in the map
     * @param key
     * @param value
     */
    public void put(K key, V value);

    /**
     * Sets an entry in the map with a String key
     * @param key
     * @param value
     */
    public void put(String key, V value);

    /**
     * Retrieve value at key
     * @param key
     * @return The value at the key
     */
    public V get(K key);

    /**
     * Retrieve value at String key
     * @param key
     * @return The value at the key
     */
    public V get(String key);

    /**
     * Remove a value at the key
     * @param key
     */
    public void remove(K key);

    /**
     * Remove a value at the String key
     * @param key
     */
    public void remove(String key);

    /**
     * Returns a Set of all the keys
     * @return Set of all the keys
     */
    public Set<K> getKeys();

    /**
     * Returns the full Map of the key-value pairs
     * @return The full Map
     */
    public Map<K, V> getMap(); 
}
