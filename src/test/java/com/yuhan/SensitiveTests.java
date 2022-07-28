package com.yuhan;

import com.yuhan.community.CommunityApplication;
import com.yuhan.community.util.SensitiveFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.annotation.Target;

/**
 * @author yuhan
 * @create 2022-07-28 21:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class SensitiveTests {

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Test
    public void testSensitiveFilter() {
        String text = "这里可以嫖娼，可以赌博，可以吸毒，哈哈哈";
        text = sensitiveFilter.filter(text);
        System.out.println(text);

        String text1 = "这里可以☆嫖☆娼☆，可以☆赌☆博☆，可以☆吸☆毒☆，哈哈哈";
        text1 = sensitiveFilter.filter(text1);
        System.out.println(text1);
    }
}
