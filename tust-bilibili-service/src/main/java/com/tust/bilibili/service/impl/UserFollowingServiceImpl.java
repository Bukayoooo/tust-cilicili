package com.tust.bilibili.service.impl;

import com.tust.bilibili.domain.User;
import com.tust.bilibili.domain.UserFollowing;
import com.tust.bilibili.domain.exception.CustomException;
import com.tust.bilibili.mapper.UserFollowingMapper;
import com.tust.bilibili.service.UserFollowingService;
import com.tust.bilibili.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserFollowingServiceImpl implements UserFollowingService{

    @Autowired
    private UserService userService;

    @Autowired
    private UserFollowingMapper userFollowingMapper;

    @Override
    @Transactional
    public void addUserFollowing(UserFollowing userFollowing){
        // 1. 获取关注的up的id，并查询其是否存在
        String followingId = userFollowing.getFollowingId();
        User userIndb = userService.getUserById(followingId);
        if(userIndb == null) {
            throw new CustomException("关注的用户不存在");
        }
        // 2. 用户存在，往用户关注表中存入新数据
        // 做一个删除功能，配合添加可以实现更新的功能
        userFollowingMapper.deleteUserFollowing(userFollowing.getUserId(), followingId);
        userFollowing.setCreateTime(new Date());
        userFollowingMapper.addUserFollowing(userFollowing);

    }
}
