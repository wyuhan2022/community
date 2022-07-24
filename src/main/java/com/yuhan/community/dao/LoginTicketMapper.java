package com.yuhan.community.dao;

import com.yuhan.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * @author yuhan
 * @create 2022-07-24 23:05
 */
@Mapper
public interface LoginTicketMapper {

    /**
     * 插入登录凭证
     * @param loginTicket
     * @return
     */
    @Insert({
            "insert into login_ticket(user_id, ticket, status, expired) ",
            "values (#{userId},#{ticket},#{status},#{expired})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    /**
     * 根据登录凭证号查找
     * @param ticket
     * @return
     */
    @Select({
            "select id, user_id, ticket, status, expired ",
            "from login_ticket where ticket=#{ticket}"
    })
    LoginTicket selectByTicket(String ticket);

    /**
     * 根据登录凭证号更新状态
     * @param ticket
     * @param status
     * @return
     */
    @Update({
            "update login_ticket set status=#{status} where ticket=#{ticket}"
    })
    int updateStatus(String ticket, int status);
}
