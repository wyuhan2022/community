package com.yuhan.community.dao;

import com.yuhan.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuhan
 * @create 2022-07-22 14:21
 */
@Mapper
public interface UserMapper {
    /**
     * 1.根据ID查询一个用户
     * @param id
     * @return
     */
    User selectById(int id);
    /**
     * 2. 根据用户名查询一个用户
     * @param userName
     * @return
     */
    User selectByUserName(String userName);
    /**
     * 3. 根据邮箱查询一个用户
     * @param email
     * @return
     */
    User selectByEmail(String email);
    /**
     * 4. 插入一个用户
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 7. 根据ID修改用户的状态
     * @param id
     * @param status
     * @return
     */
    int updateStatus(int id, int status);

    /**
     * 6. 根据ID修改用户的头像路径
     * @param id
     * @param headerUrl
     * @return
     */
    int updateHeaderUrl(int id, String headerUrl);

    /**
     * 7. 根据ID修改用户的密码
     * @param id
     * @param password
     * @return 0/1
     */
    int updatePassword(int id, String password);
}
