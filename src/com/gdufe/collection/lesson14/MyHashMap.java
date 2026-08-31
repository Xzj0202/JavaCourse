package com.gdufe.collection.lesson14;

import java.util.Objects;

public class MyHashMap<K, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] table;
    private int size;

    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    public MyHashMap() {
        @SuppressWarnings("unchecked")
        Node<K, V>[] t = (Node<K, V>[]) new Node[DEFAULT_CAPACITY];
        table = t;
    }

    private int indexFor(Object key) {
        if (key == null) {
            return 0;
        }
        return key.hashCode() & (table.length - 1);
    }

    public void put(K key, V value) {
        int idx = indexFor(key);

        for (Node<K, V> n = table[idx]; n != null; n = n.next) {
            if (Objects.equals(n.key, key)) {
                n.value = value;
                return;
            }
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = table[idx];
        table[idx] = newNode;
        ++size;

        if (size > table.length * LOAD_FACTOR) {
            resize();
        }
    }

    public V get(Object key) {
        int idx = indexFor(key);
        for (Node<K, V> n = table[idx]; n != null; n = n.next) {
            if (Objects.equals(n.key, key)) {
                return n.value;
            }
        }
        return null;
    }

    public V remove(Object key) {
        int idx = indexFor(key);
        Node<K, V> prev = null;
        for (Node<K, V> n = table[idx]; n != null; prev = n, n = n.next) {
            if (Objects.equals(n.key, key)) {
                if (prev == null) {
                    table[idx] = n.next;
                } else {
                    prev.next = n.next;
                }
                --size;
                return n.value;
            }
        }
        return null;
    }

    private void resize() {
        @SuppressWarnings("unchecked")
        Node<K, V>[] newTable = (Node<K, V>[]) new Node[table.length * 2];

        for (Node<K, V> bucket : table) {
            for (Node<K, V> n = bucket; n != null;) {
                Node<K, V> next = n.next;
                int idx = (n.key == null) ? 0 : n.key.hashCode() & (newTable.length - 1);
                n.next = newTable[idx];
                newTable[idx] = n;
                n = next;
            }
        }
        table = newTable;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return table.length;
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        System.out.println("初始容量: " + map.capacity()); // 16

        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        System.out.println(map.get("one"));
        System.out.println(map.get("two"));

        map.put("two", 22);
        System.out.println(map.get("two"));

        map.remove("one");
        System.out.println(map.get("one"));

        for (int i = 0; i < 30; ++i) {
            map.put("k" + i, i);
        }
        System.out.println("大量元素后的容量: " + map.capacity());
        System.out.println("size: " + map.size());
        System.out.println("抽查 k27: " + map.get("k27"));
    }
}
