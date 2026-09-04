package com.gdufe.test.lesson22;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import com.gdufe.collection.lesson13.MyArrayList;

// 覆盖对照表（public 方法 → 哪条测试摸过它）
// 无参构造          → newListStartsEmptyAtCapacity10
// MyArrayList(int)  → specifiedCapacityIsHonored
// add               → addAndGetInOrder, addTriggersGrowFrom10To15
// get               → addAndGetInOrder, getOnEmptyListThrows（越界 lesson20 已测，算继承）
// remove            → removeTwiceStillShifts（遗留：返回值未断言、越界未测）
// size / capacity   → 各条测试反复断言

public class MyArrayListTest {

    // 默认初始化测试
    @Test
    void newListStartsEmptyAtCapacity10() {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertEquals(10, list.capacity());
        assertEquals(0, list.size());
    }

    // 指定容量构造器：给多少容量，就兑现多少
    @Test
    void specifiedCapacityIsHonored() {
        MyArrayList<Integer> list = new MyArrayList<>(5);
        assertEquals(5, list.capacity());
        assertEquals(0, list.size());
    }

    // 取空表测试
    @Test
    void getOnEmptyListThrows() {
        MyArrayList<Integer> list = new MyArrayList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    // add和get测试
    @Test
    void addAndGetInOrder() {
        MyArrayList<Integer> list = new MyArrayList<>();

        for (int i = 0; i < 5; ++i) {
            list.add(i + 1);
        }

        for (int i = 0; i < 5; ++i) {
            assertEquals(i + 1, list.get(i));
        }

        assertEquals(5, list.size());
    }

    // remove测试
    @Test
    void removeTwiceStillShifts() {
        MyArrayList<Integer> list = new MyArrayList<>();

        for (int i = 0; i < 5; ++i) {
            list.add(i + 1);
        }

        assertEquals(3, list.get(2));

        list.remove(1);
        assertEquals(4, list.get(2));
        assertEquals(4, list.size());

        list.remove(1);
        assertEquals(5, list.get(2));
        assertEquals(3, list.size());
    }

    // grow测试
    @Test
    void addTriggersGrowFrom10To15() {
        MyArrayList<Integer> list = new MyArrayList<>();

        for (int i = 0; i < 10; ++i) {
            list.add(i + 1);
        }
        assertEquals(10, list.size());
        assertEquals(10, list.capacity());

        list.add(11);
        assertEquals(11, list.size());
        assertEquals(15, list.capacity());
    }
}
