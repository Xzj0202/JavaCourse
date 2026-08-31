package com.gdufe.thread.lesson15;

public class ThreadDemo {
    static class Printer implements Runnable {
        private final String name;

        Printer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; ++i) {
                System.out.println("我是" + name + ": " + i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Printer("线程A"));
        Thread t2 = new Thread(new Printer("线程B"));
        Thread t3 = new Thread(new Printer("线程C"));

        t1.start();
        t2.start();
        t3.start();

        // 教学演示点（8-28 现象）：join 全注释掉 → main 抢跑，「全部干完」先打印；
        // 解开注释 → main 原地等三个线程干完才打印。想验证随时解开跑一次。
        // t1.join();
        // t2.join();
        // t3.join();
        System.out.println("全部干完");

    }
}
