package com.gdufe.collection;

public class MyArrayList<E> {
    private Object[] elementData;
    private int size;

    public MyArrayList() {
        // 默认容量 10
        this.elementData = new Object[10];
        this.size = 0;
    }

    public MyArrayList(int capacity) {
        // 指定容量
        this.elementData = new Object[capacity];
        this.size = 0;
    }

    public void add(E e) {
        if (size == elementData.length) {
            grow();
        }
        elementData[size++] = e;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        return (E) elementData[index];
    }

    public int size() {
        // TODO: 一行
        return size;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        E num = (E) elementData[index];
        for (int i = index; i < size - 1; ++i) {
            elementData[i] = elementData[i + 1];
        }
        elementData[--size] = null;
        return num;
    }

    private void grow() {
        // TODO: 造 1.5 倍新数组 + 复制旧元素 + 换指向
        Object[] new_elementData = new Object[(int) (size * 1.5)];
        for (int i = 0; i < size; ++i) {
            new_elementData[i] = elementData[i];
        }
        elementData = new_elementData;
    }

    public int capacity() {
        // 测试用：返回当前容量（真 ArrayList 没有这个方法，我们用它观察扩容）
        return elementData.length;
    }

    public static void main(String[] args) {
        // 测试 1：扩容观察——加到第 11 个时容量应从 10 变 15
        MyArrayList<Integer> list = new MyArrayList<>();
        for (int i = 1; i <= 10; ++i) {
            list.add(i);
        }
        System.out.println("10 个元素后的容量: " + list.capacity());
        list.add(11);
        System.out.println("第 11 个元素后的容量: " + list.capacity());

        // 测试 2：存取正确——1~20 顺序放进去，逐个 get 出来
        MyArrayList<Integer> big = new MyArrayList<>();
        for (int i = 1; i <= 20; ++i) {
            big.add(i);
        }
        for (int i = 0; i < big.size(); ++i) {
            System.out.print(big.get(i) + " ");
        }
        System.out.println();

        // 测试 3：remove 搬家——{1,2,3,4,5} 删下标 1，应剩 {1,3,4,5}，size=4
        MyArrayList<Integer> small = new MyArrayList<>();
        small.add(1);
        small.add(2);
        small.add(3);
        small.add(4);
        small.add(5);
        small.remove(1);
        for (int i = 0; i < small.size(); ++i) {
            System.out.print(small.get(i) + " ");
        }
        System.out.println("size=" + small.size());

        // 测试 4：边界异常——get(-1) 预期抛红字，程序在此终止（这是预期的成功）
        System.out.println("测试 get(-1)，预期红字：");
        small.get(-1);
    }
}
