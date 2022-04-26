package com.tust.bilibili.controller;

import com.tust.bilibili.controller.support.UserSupport;
import com.tust.bilibili.domain.JsonData;
import com.tust.bilibili.domain.User;
import com.tust.bilibili.domain.UserInfo;
import com.tust.bilibili.domain.vo.UserVO;
import com.tust.bilibili.service.UserService;
import com.tust.bilibili.utils.RSAUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录注册模块
 */
@RestController
public class UserController{

    @Autowired
    private UserService userService;

    @Autowired
    private UserSupport userSupport;

    /**
     * 从服务器获取RSA公钥
     */
    @GetMapping("/rsa-pks")
    public JsonData<String> getRSApublicKey(){
        String publicKey = RSAUtil.getPublicKeyStr();
        return new JsonData<>(publicKey);
    }

    /**
     * 根据请求头的token获取用户信息
     */
    @GetMapping("/users")
    public JsonData<UserVO> getUserInfo() {
        Long userId = userSupport.getUserIdByToken();
        UserVO userVO = userService.getUserInfoById(userId);
        return new JsonData<>(userVO);
    }

    /**
     * 注册接口
     */
    @PostMapping("/users")
    public JsonData<String> register(@RequestBody User user) {
        userService.addUser(user);
        return JsonData.success();
    }

    /**
     * 登录接口
     */
    @PostMapping("/user-tokens")
    public JsonData<String> login(@RequestBody User user) throws Exception{
        String token = userService.login(user);
        return JsonData.success(token);
    }

    /**
     * 更新用户邮箱密码接口
     */
    @PutMapping("/users")
    public JsonData<String> updateUser(@RequestBody User user){
        // 1. 从token中获取用户id
        Long userId = userSupport.getUserIdByToken();
        // 2. 更改用户手机号密码邮箱
        user.setId(userId);
        userService.updateUser(user);
        return JsonData.success();
    }

    /**
     * 更新用户基本信息
     */
    @PutMapping("/user-infos")
    public JsonData<String> updateUserInfo(@RequestBody UserInfo userInfo) {
        // 1. 从token中获取用户id
        Long userId = userSupport.getUserIdByToken();
        userInfo.setUserId(userId);
        // 2. 更改信息
        userService.updateUserInfo(userInfo);
        return JsonData.success();
    }


}
