package com.tust.bilibili.controller;

import com.tust.bilibili.controller.support.UserSupport;
import com.tust.bilibili.domain.JsonData;
import com.tust.bilibili.domain.User;
import com.tust.bilibili.domain.UserFollowing;
import com.tust.bilibili.service.UserFollowingService;
import com.tust.bilibili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserFollowingController {

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private UserFollowingService userFollowingService;

    /**
     * 添加好友接口
     */
    @PostMapping("user-following")
    public JsonData<String> addUserFollowing(@RequestBody UserFollowing userFollowing) {
        // 1. 从token中获取用户id
        Long userId = userSupport.getUserIdByToken();
        userFollowing.setUserId(userId);
        // 2. 往用户关注表中添加一条新的好友关系数据
        userFollowingService.addUserFollowing(userFollowing);
        return JsonData.success();
    }

}
