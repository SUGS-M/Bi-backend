package com.backend.project;



import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TokenBucket1 {

    private final int capacity; // 令牌桶容量
    private final int rate; // 令牌生成速率
    private int tokens; // 当前令牌数量

    public TokenBucket1(int capacity, int rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.tokens = capacity;
        startTokenGenerator();
    }

    public boolean getToken() {
        synchronized (this) {
            if (tokens > 0) {
                tokens--;
                return true;
            }
        }
        return false;
    }

    private void startTokenGenerator() {
        Thread generator = new Thread(() -> {
            Lock lock = new ReentrantLock();
            while (true) {
                lock.tryLock();
                try {
                    if (tokens < capacity) {
                        tokens++;
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                try {
                    TimeUnit.SECONDS.sleep(1 / rate);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        generator.start();
    }

    public static void main(String[] args) {
        TokenBucket1 tokenBucket = new TokenBucket1(10, 5);

        while (true){
            for (int i = 0; i < 20; i++) {
                if (tokenBucket.getToken()) {
                    System.out.println("处理请求：" + i);
                } else {
                    System.out.println("限流，拒绝请求：" + i);
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1/5);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

