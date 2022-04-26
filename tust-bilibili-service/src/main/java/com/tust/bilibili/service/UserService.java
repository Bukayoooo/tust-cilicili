package com.tust.bilibili.service;

import com.tust.bilibili.domain.User;
import com.tust.bilibili.domain.UserFollowing;
import com.tust.bilibili.domain.UserInfo;
import com.tust.bilibili.domain.vo.UserVO;

public interface UserService{

    void addUser(User user);

    String login(User user) throws Exception;

    UserVO getUserInfoById(Long userId);

    void updateUser(User user);

    void updateUserInfo(UserInfo userInfo);

    User getUserById(String user);
}
