package com.gdufe.thread;

public class Lesson14 {
    static Object lock1 = new Object(); // 锁1
    static Object lock2 = new Object(); // 锁2

    // 线程A：先获取lock1，再获取lock2
    static class TaskA implements Runnable {
        public void run() {
            synchronized (lock1) {
                System.out.println("线程A获取了lock1");
                try {
                    Thread.sleep(100); // 短暂等待，让线程B有机会获取lock2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程A尝试获取lock2...");
                synchronized (lock2) {
                    System.out.println("线程A获取了lock2，完成工作");
                }
            }
        }
    }

    // 线程B：先获取lock2，再获取lock1
    static class TaskB implements Runnable {
        public void run() {
            synchronized (lock2) {
                System.out.println("线程B获取了lock2");
                try {
                    Thread.sleep(100); // 短暂等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程B尝试获取lock1...");
                synchronized (lock1) {
                    System.out.println("线程B获取了lock1，完成工作");
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建线程A和线程B的任务
        Runnable taskA = new TaskA();
        Runnable taskB = new TaskB();

        // 创建线程对象
        Thread threadA = new Thread(taskA);
        Thread threadB = new Thread(taskB);

        // 启动线程
        threadA.start();
        threadB.start();

        // 等待1秒让死锁形成
        Thread.sleep(1000);

        // 提示如何分析死锁
        System.out.println("\n=== 程序已卡住，使用jstack分析 ===");
        System.out.println("打开新的终端窗口，执行：");
        System.out.println("jstack " + threadA.getId() + " > deadlock.txt");
    }
}
