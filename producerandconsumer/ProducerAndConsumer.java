package com.company.producerandconsumer;
//提供了消费者-生产者的一个实现方案

import java.util.LinkedList;

public class ProducerAndConsumer {
    // 定义一个共享缓冲区
    private LinkedList<Integer> buffer = new LinkedList<>();
    // 定义一个缓冲区最大容量
    private static final int MAX_CAPACITY = 10;

    // 生产者线程向共享缓冲区添加元素
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                // 如果缓冲区已满，则等待
                while (buffer.size() == MAX_CAPACITY) {
                    wait();
                }
                System.out.println("Producer produced-" + value);
                buffer.add(value++);
                notifyAll();  // 唤醒所有正在等待的线程
                Thread.sleep(1000);  // 休眠1秒
            }
        }
    }

    // 消费者线程从共享缓冲区读取元素
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // 如果缓冲区为空，则等待
                while (buffer.size() == 0) {
                    wait();
                }
                int value = buffer.removeFirst();
                System.out.println("Consumer consumed-" + value);
                notifyAll();  // 唤醒所有正在等待的线程
                Thread.sleep(1000);  // 休眠1秒
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ProducerAndConsumer pc = new ProducerAndConsumer();

        // 创建生产者线程并启动
        Thread producerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 创建消费者线程并启动
        Thread consumerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 启动线程
        producerThread.start();
        consumerThread.start();

        // 主线程等待子线程结束
        producerThread.join();
        consumerThread.join();
    }
}
