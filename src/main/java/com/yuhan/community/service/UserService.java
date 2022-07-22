package com.yuhan.community.service;

import com.yuhan.community.dao.UserMapper;
import com.yuhan.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuhan
 * @create 2022-07-22 21:45
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int userId) {
        return userMapper.selectById(userId);
    }
}
