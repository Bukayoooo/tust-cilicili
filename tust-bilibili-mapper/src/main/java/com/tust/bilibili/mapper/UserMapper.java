package com.tust.bilibili.mapper;

import com.tust.bilibili.domain.User;
import com.tust.bilibili.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper{
    User getUserByPhone(String phone);

    void addUser(User user);

    void addUserInfo(UserInfo userInfo);

    User getUserByPhoneOrEmail(String phone, String email);

    UserInfo getUserInfoById(Long userId);

    User getUserById(Long userId);

    void updateUser(User user);

    void updateUserInfo(UserInfo userInfo);
}
