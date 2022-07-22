package com.yuhan.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @author yuhan
 * @create 2022-07-21 21:17
 */
@Repository
public class AlphaDaoMybatisImpl implements AlphaDao{
    @Override
    public String select() {
        return "MyBatis";
    }
}
