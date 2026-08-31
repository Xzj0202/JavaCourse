package com.gdufe.collection.lesson14;

// 接口 = 合同：凡是「形状」，必须提供 area() 方法。
// 谁 implements 这个接口，谁就得把 area() 写出来，否则编译不过。
public interface Shape {
    double area();
}
