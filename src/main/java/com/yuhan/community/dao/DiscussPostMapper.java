package com.yuhan.community.dao;

import com.yuhan.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yuhan
 * @create 2022-07-22 20:48
 */
@Mapper
public interface DiscussPostMapper {
    /**
     * select all posts or user's posts by userId
     * @param userId
     * @param offset
     * @param limit
     * @return
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    /**
     * select all post's number or user's post number
     * @param userId
     * @return
     */
    int selectDiscussPostRows(@Param("userId") int userId);//@Param注解用于给参数起别名，如果只有一个参数，并且在<if>里使用，则必须加别名


    /**
     * insert a discussPost
     * @param discussPost
     * @return
     */
    int insertDiscussPost(DiscussPost discussPost);

    /**
     * select discussPost by id
     * @param id
     * @return
     */
    DiscussPost selectDiscussPost(int id);
}
