package com.qfh.designmode.producerandconsumer.method1;

import java.util.Queue;
import java.util.Random;

/*
 * @Description
 * @Author alvin
 * @Date 2019-03-12 10:37:10
 */
public class Producer extends Thread {
    private Queue<Integer> queue;
    String name;
    int maxSize;
    int i = 0;

    public Producer(Queue<Integer> queue, String name, int maxSize) {
        super(name);
        this.queue = queue;
        this.name = name;
        this.maxSize = maxSize;
    }

    @Override
    public void run() {
        while (true){
            synchronized (queue){
                while (queue.size() == maxSize){
                    try {
                        System.out.println("Queue is full,Producer[" + name + "] thread waiting for consumer to take something from queue.");
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                int x = queue.poll();
                System.out.println("[" + name + "] Producing value:" + x);
                queue.offer(i++);
                queue.notifyAll();

                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}























