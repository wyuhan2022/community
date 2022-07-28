package com.yuhan.community.controller;

import com.yuhan.community.entity.DiscussPost;
import com.yuhan.community.entity.Page;
import com.yuhan.community.entity.User;
import com.yuhan.community.service.DiscussPostService;
import com.yuhan.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuhan
 * @create 2022-07-22 20:20
 */
@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    /**
     * 访问首页
     * @param model
     * @param page
     * @return
     */
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPosts(Model model, Page page) {
        //方法调用前，SpringMVC会自动实例化Model和Page，并将Page注入Model。
        //所以，在Thymeleaf中可以直接访问Page对象中的数据
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");
        List<DiscussPost> posts = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if(posts != null){
            for (DiscussPost post : posts) {
                Map<String, Object> map = new HashMap<>(16);
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "/index";
    }
}
