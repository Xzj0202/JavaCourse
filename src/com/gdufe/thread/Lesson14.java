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
                    Thread.sleep(100); // 短暂等待，制造线程交错
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

    // 线程B：先获取lock1，再获取lock2（与A同序，破坏循环等待——死锁预防版）
    static class TaskB implements Runnable {
        public void run() {
            synchronized (lock1) {
                System.out.println("线程B获取了lock1");
                try {
                    Thread.sleep(100); // 短暂等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程B尝试获取lock2...");
                synchronized (lock2) {
                    System.out.println("线程B获取了lock2，完成工作");
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

        threadA.join();
        threadB.join();
        System.out.println("\n=== 程序正常结束：统一加锁顺序 → 循环等待被破坏 → 死锁不再出现 ===");

        // 想复现死锁做 jstack 实操：把 TaskB 的两个 synchronized 块对调回「先 lock2 后 lock1」，
        // 跑起来卡住后另开终端执行（jstack 要进程 pid，不是线程 id；Windows 完全可用）：
        //   第 1 步：jps -l        ← 找到本程序的 pid（认准 Lesson14 那行）
        //   第 2 步：jstack <pid>  ← 拉到最底看 Found one Java-level deadlock
    }
}
