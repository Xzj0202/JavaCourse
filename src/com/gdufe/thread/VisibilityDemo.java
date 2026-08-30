package com.gdufe.thread;

public class VisibilityDemo {
    static volatile boolean running = true; // 第一轮不加 volatile；第二轮改成 static volatile boolean running = true;

    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            long n = 0;
            while (running) { // 空1：worker 一直转的条件是什么？
                n++; // 空转计数，证明它活着
            }
            System.out.println("worker 停下，共数到 " + n);
        });

        worker.start(); // 空2：启动线程（不是 run！）

        Thread.sleep(1000); // 让 worker 先跑 1 秒

        running = false; // 空3：main 想叫停 worker，改成什么？
        System.out.println("main：running 已置 false");

        worker.join(); // 空4：main 原地等 worker 结束

        System.out.println("main：结束");
    }
}
