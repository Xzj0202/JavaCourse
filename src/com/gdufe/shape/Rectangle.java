package com.gdufe.shape;

// Rectangle 也签了 Shape 合同：同样必须实现 area()
public class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}
