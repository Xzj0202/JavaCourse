package com.gdufe.thread.lesson16;

public class WaitNotifyDemo {
    static boolean flag = false; // 共享条件：A 要等 flag 变 true 才能继续
    static Object lock = new Object();

    // 定义线程A的任务
    static class TaskA implements Runnable {
        public void run() {
            synchronized (lock) {
                System.out.println("线程A开始等待...");
                try {
                    // 必须用while循环判断，防止虚假唤醒
                    while (!flag) {
                        lock.wait(); // 释放锁并等待
                    }
                    System.out.println("线程A执行了！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 定义线程B的任务
    static class TaskB implements Runnable {
        public void run() {
            synchronized (lock) {
                try {
                    // 等待1秒，让线程A先进入等待状态
                    Thread.sleep(1000);
                    flag = true; // 设置条件
                    System.out.println("线程B设置flag=true");
                    lock.notify(); // 唤醒线程A
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        // 创建线程A和线程B的任务
        Runnable taskA = new TaskA();
        Runnable taskB = new TaskB();

        // 创建线程对象
        Thread threadA = new Thread(taskA);
        Thread threadB = new Thread(taskB);

        // 启动线程
        threadA.start();
        threadB.start();
    }
}
