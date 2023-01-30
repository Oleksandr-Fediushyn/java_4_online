package ua.com.alevel;

import java.util.*;

public class Dictionary<K, V> {

    private final static int TABLE_SIZE = 128;
    HashEntry<K, V>[] table;
    int size;

    Dictionary() {
        table = new HashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }

    int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(K key) {
        int i = hash(key);
        return table[i] != null && table[i].getKey() == key;
    }
    public boolean containsValue(V value) {
        for (int i = 0; i < table.length - 1; i++) {
            if (table[i] == null) continue;
            else if (table[i].getValue().equals(value)) return true;
        }
        return false;
    }

    public boolean clear() {
        boolean flag = true;
        for (int i = 0; i < table.length - 1; i++)
            table[i] = null;
        size = 0;
        return flag;
    }

    public boolean put(K key, V value) {
        int i = hash(key);
        while (table[i] != null && table[i].getKey() != key)
            i = (i + 1) % TABLE_SIZE;
        table[i] = new HashEntry<>(key, value);
        if (table[i] != null) {
            size++;
            return true;
        } else {
            return false;
        }
    }

    public boolean putAll(Dictionary<K, V> dictionary) {
        if (this == dictionary) return false;
        for (int i = 0; i < dictionary.table.length - 1; i++) {
            if (dictionary.table[i] == null) continue;
            else this.put(dictionary.table[i].getKey(), dictionary.table[i].getValue());
            //а почему не сделать так - if (table[i] != null) { this.put(dictionary.table[i].getKey(), dictionary.table[i].getValue()) }
        }
        return true;
    }

    public V get(K key) {
        try {
            if (!containsKey(key)) throw new RuntimeException();
        } catch (RuntimeException e) {
            System.out.println("This key is invalid");
        }
        int i = hash(key);
        while (table[i] != null && table[i].getKey() != key)
            i = (i + 1) % TABLE_SIZE;
        if (table[i] == null)
            return null;
        else {
            return table[i].getValue();
        }
    }

    public boolean remove(K key) {
        int c = hash(key);
        if (table[c] == null) {
            System.out.println("This entry not issue !");
            return false;
        } else if (table[c].getKey().equals(key))  table[c] = null;
            size--;
            System.out.println("Entry deleted successfully !");
            return true;
    }

    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>();
        for (int i = 0; i < table.length - 1; i++) {
            if (table[i] == null) continue;
            else keys.add(table[i].getKey());
            //а почему не сделать так - if (table[i] != null) { keys.add(table[i].getKey()) }
        }
        return keys;
    }

    public Collection<V> values() {
        final Collection<V> colValues = new ArrayList<V>();
        for (int i = 0; i < table.length - 1; i++) {
            if (table[i] == null) continue;
            else colValues.add(table[i].getValue());
            //а почему не сделать так - if (table[i] != null) { colValues.add(table[i].getValue()) }
        }
        return colValues;
    }
    private int hash(K key) {
        int hashCod = key.hashCode();
        return Math.abs(hashCod % TABLE_SIZE);
    }

    private class HashEntry<K, V> {
        private K key;
        private V value;
        HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
    }
}
