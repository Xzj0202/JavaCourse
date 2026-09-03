package com.gdufe.test.lesson20;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import com.gdufe.collection.lesson13.MyArrayList;

public class MyArrayListTest {

    @Test
    void addTriggersGrow() {
        MyArrayList<Integer> list = new MyArrayList<>();
        // TODO 1: 循环 add 1~10，断言 capacity() 还是 10
        for (int i = 1; i <= 10; ++i) {
            list.add(i);
        }
        assertEquals(10, list.capacity());
        // TODO 2: add 第 11 个，断言 capacity() 变 15
        list.add(11);
        assertEquals(15, list.capacity());
    }

    @Test
    void addAndGetInOrder() {
        MyArrayList<Integer> big = new MyArrayList<>();
        // TODO: add 1~20，循环断言每个 get(i) == i + 1
        for (int i = 0; i < 20; ++i) {
            big.add(i + 1);
            assertEquals(i + 1, big.get(i));
        }
    }

    @Test
    void removeShiftsElements() {
        MyArrayList<Integer> small = new MyArrayList<>();
        // TODO: add 1~5，remove(1)
        // 断言: size() == 4，且 get(0)==1, get(1)==3, get(2)==4, get(3)==5
        for (int i = 1; i <= 5; ++i) {
            small.add(i ) ;
        }
        small.remove(1);
        assertEquals(4, small.size());
        assertEquals(1, small.get(0));
        assertEquals(3, small.get(1));
        assertEquals(4, small.get(2));
        assertEquals(5, small.get(3));
    }

    @Test
    void getOutOfBoundsThrows() {
        MyArrayList<Integer> small = new MyArrayList<>();
        small.add(1);
        // TODO 1: assertThrows(IndexOutOfBoundsException.class, () -> small.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> small.get(-1));
        // TODO 2: 越上界 get(1) 也断言抛同样的异常
        assertThrows(IndexOutOfBoundsException.class, () -> small.get(1));
    }
}
