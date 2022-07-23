package com.yuhan;

import com.yuhan.community.CommunityApplication;
import com.yuhan.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author yuhan
 * @create 2022-07-23 21:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;
    @Test
    public void testTextMail(){
        mailClient.sendMail("wyuhan2022xz@163.com","Test","Welcome");
    }

    @Test
    public void testHTMLMail(){
        Context context = new Context();
        context.setVariable("username","yuhan");

        String content = templateEngine.process("/mail/demo", context);
        mailClient.sendMail("wyuhan2022xz@163.com","HTML",content);
    }
    
}
