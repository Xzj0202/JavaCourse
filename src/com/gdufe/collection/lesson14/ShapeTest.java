package com.gdufe.collection.lesson14;

public class ShapeTest {
    public static void main(String[] args) {
        // 数组类型是 Shape（合同），里面装的是具体形状（实现）
        // 循环里统一调 area() —— 每个元素按自己的实现算面积，这就是多态
        Shape[] shapes = { new Circle(2), new Rectangle(3, 4) };
        for (Shape s : shapes) {
            System.out.println(s.area());
        }
    }
}
