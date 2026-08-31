package com.gdufe.thread.lesson18;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicRace {
    // TODO 1: 声明 static AtomicInteger count = new AtomicInteger(0);
    static AtomicInteger count = new AtomicInteger(0);

    static class Worker implements Runnable {
        public void run() {
            for (int i = 0; i < 20000; i++) {
                // TODO 2: 一行：count.incrementAndGet(); ——它就是原子版 count++
                count.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // TODO 3: 抄 RaceDemo 的 main 壳：3 个 Worker，start 后 join
        // TODO 4: 打印 System.out.println("count = " + count.get());
        // 注意是 count.get()，不是直接打印 count
        Thread t1 = new Thread(new Worker());
        Thread t2 = new Thread(new Worker());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("count = " + count.getAndIncrement());
    }
}
