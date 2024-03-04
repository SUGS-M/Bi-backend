package com.backend.project;

import java.util.concurrent.TimeUnit;

public class LeakyBucket {
    private int capacity;  // 漏桶容量
    private int rate;  // 漏桶流出速率
    private int water;  // 当前水量
    private long lastTime;  // 上次请求时间

    public LeakyBucket(int capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.water = 0;
        this.lastTime = System.currentTimeMillis();
    }

    public synchronized boolean processRequest() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = TimeUnit.MILLISECONDS.toSeconds(currentTime - lastTime);
        lastTime = currentTime;
        water = Math.max(0, water - (int) (elapsedTime * rate));

        if (water < capacity) {
            water++;
            return true;  // 接收新的请求
        } else {
            return false;  // 漏桶已满，拒绝新的请求
        }
    }

    // 测试漏桶算法
    public static void main(String[] args) throws InterruptedException {
        LeakyBucket bucket = new LeakyBucket(10, 1);  // 漏桶容量为10，流出速率为1个/秒
        int[] requests = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};  // 模拟15个请求

        for (int i : requests) {
            if (bucket.processRequest()) {
                System.out.println("Request " + i + " is processed successfully.");
            } else {
                System.out.println("Request " + i + " is rejected.");
            }
            TimeUnit.MILLISECONDS.sleep(500);  // 模拟请求间隔时间
        }
    }
}
