package com.gdufe.test.lesson21;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import com.gdufe.collection.lesson14.MyHashMap;

public class MyHashMapTest {

    @Test
    void putAndGetBasic() {
        // 搭台 + 演出：存三个
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        // 判卷：逐个取回，再验 size
        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        assertEquals(3, map.get("three"));
        assertEquals(3, map.size());
    }

    @Test
    void putSameKeyUpdatesValue() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("two", 2);

        map.put("two", 22);

        assertEquals(22, map.get("two"));
        assertEquals(1, map.size());
    }

    @Test
    void getMissingKeyReturnsNull() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);

        assertNull(map.get("nobody"));
    }

    @Test
    void removeReturnsValueAndCleansUp() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("a", 1);
        map.put("b", 2);

        assertEquals(2, map.remove("b"));
        assertNull(map.get("b"));
        assertEquals(1, map.size());
    }

    @Test
    void removeMissingKeyReturnsNull() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("a", 1);

        assertNull(map.remove("nobody"));
        assertEquals(1, map.size());
    }

    @Test
    void put13thTriggersGrow() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 12; ++i) {
            map.put("k" + i, i);
        }
        assertEquals(16, map.capacity());

        map.put("k12", 12);

        assertEquals(32, map.capacity());
    }

    @Test
    void oldDataSurvivesResize() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 20; ++i) {
            map.put("k" + i, i);
        }

        for (int i = 0; i < 20; ++i) {
            assertEquals(i, map.get("k" + i));
        }
        assertEquals(20, map.size());
    }

    @Test
    void nullKeyCanBeStoredAndFetched() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put(null, 99);

        assertEquals(99, map.get(null));
    }
}
