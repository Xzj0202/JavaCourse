package com.gdufe.thread.lesson15;

public class RaceDemo {
    static int count = 0;

    static synchronized void add() {
        count++;
    }

    static class Worker implements Runnable {
        public void run() {
            for (int i = 0; i < 10000; ++i) {
                add();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Worker());
        Thread t2 = new Thread(new Worker());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("count = " + count);
    }
}
