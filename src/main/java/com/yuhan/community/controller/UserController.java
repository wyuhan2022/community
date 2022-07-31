package com.yuhan.community.controller;

import com.yuhan.community.annotation.LoginRequired;
import com.yuhan.community.entity.LoginTicket;
import com.yuhan.community.entity.User;
import com.yuhan.community.service.FollowService;
import com.yuhan.community.service.LikeService;
import com.yuhan.community.service.UserService;
import com.yuhan.community.util.CommunityConstant;
import com.yuhan.community.util.CommunityUtil;
import com.yuhan.community.util.HostHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author yuhan
 * @create 2022-07-25 23:38
 */
@Controller
@RequestMapping("/user")
public class UserController implements CommunityConstant {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private FollowService followService;
    @Autowired
    private HostHolder hostHolder;
    @Value("${community.path.upload}")
    private String uploadPath;
    @Value("${community.path.domain}")
    private String domain;
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 访问设置页面
     * @return
     */
    @RequestMapping(path = "/setting", method = RequestMethod.GET)
    @LoginRequired
    public String getSettingPage(){
        return "/site/setting";
    }


    /**
     * 上传头像
     * @param headerImage
     * @param model
     * @return
     */
    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    @LoginRequired
    public String uploadHeader(MultipartFile headerImage, Model model){
        if (headerImage == null){
            model.addAttribute("error","你还没有选择图片！");
            return "/site/setting";
        }
        String filename = headerImage.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        if (StringUtils.isBlank(suffix)){
            model.addAttribute("error","文件格式错误！");
            return "/site/setting";
        }

        // 生成随机文件名
         filename = CommunityUtil.generateUUID() + suffix;
        // 确定文件存放路径
        File dest = new File(uploadPath + "/" + filename);
        try {
            // 存放文件
            headerImage.transferTo(dest);
        } catch (IOException e) {
            logger.error("上传文件失败：" + e.getMessage());
            throw new RuntimeException("上传文件失败，服务器发生异常！");
        }

        //更新当前用户的头像的路径（web访问路径）
        User user = hostHolder.getUser();
        String headerUrl = domain + contextPath + "/user/header/" + filename;
        userService.updateHeader(user.getId(), headerUrl);

        return "redirect:/index";
    }

    /**
     * 页面上获取头像
     * @param filename
     * @param response
     */
    @RequestMapping(path = "/header/{filename}", method = RequestMethod.GET)
    public void getHeader(@PathVariable("filename") String filename, HttpServletResponse response){
        // 服务器存放路径
        filename = uploadPath + "/" + filename;
        // 文件路径
        String suffix = filename.substring(filename.lastIndexOf("."));
        // 响应图片
        response.setContentType("image/" + suffix);
        try (
                OutputStream outputStream = response.getOutputStream();
                FileInputStream fileInputStream = new FileInputStream(filename);
                ){
            byte[] buffer = new byte[1024];
            int b = 0;
            while ((b = fileInputStream.read(buffer)) != -1){
                outputStream.write(buffer,0, b);
            }
        } catch (IOException e) {
            logger.error("读取头像失败：" + e.getMessage());
        }
    }

    /**
     * 个人主页
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(path = "/profile/{userId}", method = RequestMethod.GET)
    public String getProfilePage(@PathVariable("userId") int userId, Model model) {
        User user = userService.findUserById(userId);
        if (user == null) {
            throw new RuntimeException("该用户不存在！");
        }

        // 用户
        model.addAttribute("user", user);
        // 点赞数量
        int likeCount = likeService.findUserLikeCount(userId);
        model.addAttribute("likeCount", likeCount);
        // 关注数量
        long followeeCount = followService.findFolloweeCount(userId, ENTITY_TYPE_USER);
        model.addAttribute("followeeCount", followeeCount);
        // 粉丝数量
        long followerCount = followService.findFollowerCount(ENTITY_TYPE_USER, userId);
        model.addAttribute("followerCount",followerCount);
        // 是否已关注
        boolean hasFollowed = false;
        if (hostHolder.getUser() != null) {
            hasFollowed = followService.hasFollowed(hostHolder.getUser().getId(), ENTITY_TYPE_USER, userId);
        }
        model.addAttribute("hasFollowed", hasFollowed);
        return "/site/profile";
    }


}
