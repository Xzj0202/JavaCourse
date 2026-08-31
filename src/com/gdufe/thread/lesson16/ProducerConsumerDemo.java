package com.gdufe.thread.lesson16;

// Lesson15: 生产者消费者模型
public class ProducerConsumerDemo {
    static class Buffer {
        private int[] buffer = new int[10]; // 缓冲区大小为10
        private int count = 0; // 当前缓冲区中的数据数量
        private int in = 0; // 下一个生产位置
        private int out = 0; // 下一个消费位置

        // 注意：没有单独的 lock 字段——synchronized 修饰方法 = synchronized(this)，
        // 锁的就是 Buffer 实例本身；wait/notify 操作的也是 this 的等待队列

        // 生产数据
        public synchronized void produce(int data) throws InterruptedException {
            // 缓冲区满了就等待
            while (count == buffer.length) {
                System.out.println("缓冲区已满，生产者等待...");
                wait();
            }
            buffer[in] = data;
            in = (in + 1) % buffer.length;
            count++;
            System.out.println("生产: " + data + " " + this);
            notify(); // 唤醒可能等待的消费者
        }

        // 消费数据
        public synchronized int consume() throws InterruptedException {
            // 缓冲区空了就等待
            while (count == 0) {
                System.out.println("缓冲区已空，消费者等待...");
                wait();
            }
            int data = buffer[out];
            out = (out + 1) % buffer.length;
            count--;
            System.out.println("消费: " + data + " " + this);
            notify(); // 唤醒可能等待的生产者
            return data;
        }

        // 辅助方法：打印缓冲区状态
        public String toString() {
            return "Buffer[count=" + count + ", in=" + in + ", out=" + out + "]";
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();

        // 生产者任务
        class Producer implements Runnable {
            private final int id;

            Producer(int id) {
                this.id = id;
            }

            public void run() {
                try {
                    for (int i = 0; i < 20; i++) {
                        buffer.produce(i + id * 100); // 生产数据：0-99, 100-199
                        Thread.sleep(50); // 稍微延迟
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // 消费者任务
        class Consumer implements Runnable {
            public void run() {
                try {
                    for (int i = 0; i < 20; i++) {
                        buffer.consume(); // 消费的内容已由 consume() 内部打印
                        Thread.sleep(100); // 消费稍慢一些
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // 创建2个生产者和2个消费者
        Thread producer1 = new Thread(new Producer(1));
        Thread producer2 = new Thread(new Producer(2));
        Thread consumer1 = new Thread(new Consumer());
        Thread consumer2 = new Thread(new Consumer());

        // 启动所有线程
        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();

        // 等待所有线程完成
        producer1.join();
        producer2.join();
        consumer1.join();
        consumer2.join();

        System.out.println("\n=== 所有任务完成 ===");
        System.out.println("总生产数: 40 (2个生产者各20个)");
        System.out.println("总消费数: 40 (2个消费者各20个)");
    }
}
