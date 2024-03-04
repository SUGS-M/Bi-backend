package com.backend.project.manage;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AiManagerTest {
    @Resource
    private AiManager aiManager;

    @Test
    void doChat() {
        long biModelId = 1659171950288818178L;
        String message = "推荐几首邓紫棋的歌";
        String message1 = "日期,用户数\n" +
                          "1号,10\n" +
                          "2号,20\n" +
                          "3号,30\n" +
                          "4号,90\n" +
                          "5号,0\n" +
                          "6号,10\n" +
                          "7号,20";
        String ans = aiManager.doChat(biModelId,message);
        String ans1 = aiManager.doChat(biModelId,message1);
        System.out.println(ans1);
    }
}