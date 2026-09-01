package com.gdufe.thread.lesson19;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

    static class Task implements Runnable {
        private final int id;

        Task(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println("任务" + id + "由" + Thread.currentThread().getName() + "执行");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("睡眠被打断");
            }

        }
    }

    public static void main(String[] args) {
        // 线程池三件套：
        ExecutorService pool = Executors.newFixedThreadPool(3); // 1) 建池：3 个 worker 线程

        for (int i = 1; i <= 10; ++i) {
            pool.submit(new Task(i)); // 2) 交任务：10 个任务进队列
        }

        // 现象实验：先不写这行，运行看程序会不会自己退出；再补上对比
        pool.shutdown();
        pool.submit(new Task(11)); // 关门之后还交活，会怎样？
    }
}
