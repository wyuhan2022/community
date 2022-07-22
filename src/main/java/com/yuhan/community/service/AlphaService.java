package com.yuhan.community.service;

import com.yuhan.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuhan
 * @create 2022-07-21 21:07
 */
@Service
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;

    public String find(){
        return alphaDao.select();
    }
}
