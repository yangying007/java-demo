package com.wangzaiplus.test.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailUtilTest {

    @Autowired
    private JedisUtil jedisUtil;

    @Test
    public void send() {
    }

    @Test
    public void sendAttachment() {
    }

    @Test
    public void setRedisTest() {
        String set = jedisUtil.set("aaa", "bbb");
        System.out.println(set);
    }


    @Test
    public void getRedisTest() {
        String aaa = jedisUtil.get("aaa");
        System.out.println(aaa);
    }
}