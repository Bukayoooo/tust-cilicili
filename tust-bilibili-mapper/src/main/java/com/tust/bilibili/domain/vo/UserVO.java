package com.tust.bilibili.domain.vo;

import com.tust.bilibili.domain.UserInfo;
import lombok.Data;

@Data
public class UserVO{

    private Long id;

    private String phone;

    private String email;

    private UserInfo userInfo;
}
