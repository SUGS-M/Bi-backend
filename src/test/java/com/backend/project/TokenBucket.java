package com.backend.project;

import com.google.common.util.concurrent.RateLimiter;


//import java.util.concurrent.TimeUnit;
//
//public class TokenBucket {
//
//    private final int capacity; // 令牌桶容量
//    private final int rate; // 令牌生成速率
//    private int tokens; // 当前令牌数量
//
//    public TokenBucket(int capacity, int rate) {
//        this.capacity = capacity;
//        this.rate = rate;
//        this.tokens = capacity;
//        startTokenGenerator();
//    }
//
//    public boolean getToken() {
//        synchronized (this) {
//            if (tokens > 0) {
//                tokens--;
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void startTokenGenerator() {
//        Thread generator = new Thread(() -> {
//            while (true) {
//                synchronized (this) {
//                    if (tokens < capacity) {
//                        tokens++;
//                    }
//                }
//                try {
//                    TimeUnit.SECONDS.sleep(1 / rate);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        generator.start();
//    }

//    public static void main(String[] args) {
//        TokenBucket tokenBucket = new TokenBucket(10, 2);
//
//        while (true){
//            for (int i = 0; i < 20; i++) {
//                if (tokenBucket.getToken()) {
//                    System.out.println("处理请求：" + i);
//                } else {
//                    System.out.println("限流，拒绝请求：" + i);
//                }
//            }
//            try {
//                TimeUnit.SECONDS.sleep(5);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//}

public class TokenBucket {
    public static void main(String[] args) throws InterruptedException {
        LeakyBucket leakyBucket = new LeakyBucket(10,1);
        int[] requests = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};  // 模拟15个请求

        for (int i : requests) {
            if (leakyBucket.processRequest()) {
                System.out.println("Request " + i + " is processed successfully.");
            } else {
                System.out.println("Request " + i + " is rejected.");
            }
            Thread.sleep(500);  // 模拟请求间隔时间
        }
    }
}


