package com.gdufe.thread.lesson19;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {

    // 和 Runnable 的区别：call() 有返回值，且声明 throws Exception
    static class SumTask implements Callable<Integer> {
        private final int n;

        SumTask(int n) {
            this.n = n;
        }

        public Integer call() {
            return (1 + n) * n / 2;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // submit 后立刻拿到一张「取货单」Future，此时任务可能还没算完
        Future<Integer> f1 = pool.submit(new SumTask(100));
        Future<Integer> f2 = pool.submit(new SumTask(200));
        Future<Integer> f3 = pool.submit(new SumTask(300));

        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());

        pool.shutdown();
    }
}
