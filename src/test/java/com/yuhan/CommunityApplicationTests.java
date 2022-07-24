package com.yuhan;

import com.yuhan.community.CommunityApplication;
import com.yuhan.community.dao.UserMapper;
import com.yuhan.community.entity.User;
import com.yuhan.community.util.CommunityUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
class CommunityApplicationTests {


    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        User user = userMapper.selectById(101);
        System.out.println(user);
    }

    @Test
    public void testCommunityUtil(){
        System.out.println(CommunityUtil.generateUUID());
    }


}
