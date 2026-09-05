package com.gdufe.collection.lesson23;

import com.gdufe.collection.lesson13.MyArrayList;

public class GenEraseDemo {
    public static void main(String[] args) {
        MyArrayList<Integer> a = new MyArrayList<>();
        MyArrayList<String> b = new MyArrayList<>();

        // TODO 1: 用 == 比较 a.getClass() 和 b.getClass()，打印结果
        System.out.println(a.getClass() == b.getClass());

        // TODO 2: 单独打印 a.getClass()，看看运行期它"叫什么"
        System.out.println(a.getClass());

        a.add(1);
        b.add("hello");

        // TODO 3: 分别 get(0) 打印 a 和 b 存的值，证明这个"同一个类"没影响各自存对类型
        System.out.println(a.get(0));
        System.out.println(b.get(0));
        String s = b.get(0); // 编译器，我盯着你
    }
}
