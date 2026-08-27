package com.gdufe.shape;

// Circle 签了 Shape 合同：必须实现 area()
public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override   // 告诉编译器「我在覆写合同里的 area()」，写错签名会报错
    public double area() {
        return Math.PI * radius * radius;
    }
}
