package com.tust.bilibili.service.impl;

import com.mysql.cj.util.StringUtils;
import com.tust.bilibili.domain.User;
import com.tust.bilibili.domain.UserFollowing;
import com.tust.bilibili.domain.UserInfo;
import com.tust.bilibili.domain.vo.UserVO;
import com.tust.bilibili.domain.exception.CustomException;
import com.tust.bilibili.mapper.UserMapper;
import com.tust.bilibili.service.UserService;
import com.tust.bilibili.utils.MD5Util;
import com.tust.bilibili.utils.RSAUtil;
import com.tust.bilibili.utils.TokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.tust.bilibili.domain.constant.UserConstant.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据手机号查询用户
     */
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    /**
     * 添加用户
     */
    @Override
    public void addUser(User user){
        // 1. 获取手机号并校验
        String phone = user.getPhone();
        if (StringUtils.isNullOrEmpty(phone)) {
            throw new CustomException("手机号不能为空");
        }
        // 2. 查询数据库，看用户是否已经注册
        User userInDB = this.getUserByPhone(user.getPhone());
        // 3. 用户已注册
        if(userInDB != null) {
            throw new CustomException("用户已经注册过");
        }
        // 4.用户未注册，对密码进行RSA解密
        String password = user.getPassword();
        String rawPassWord = "";     // RSA解码后的密码
        try {
            rawPassWord = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new CustomException("密码RSA解析失败");
        }
        // 5. 对密码进行MD5加密
        Date now = new Date();
        String salt = String.valueOf(now.getTime());    // 使用当前时间生成盐值
        String md5Password = MD5Util.sign(rawPassWord, salt, "UTF-8");
        // 6. 往数据库t_user表中添加新用户
        user.setSalt(salt);
        user.setCreateTime(now);
        user.setPassword(md5Password);
        userMapper.addUser(user);
        // 7. 往数据库t_user_info表中添加新用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setNick(user.getNickName());
        userInfo.setBirth(DEFAULT_BIRTH_TIME);
        userInfo.setGender(DEFAULT_GENDER_MALE);
        userInfo.setCreateTime(now);
        userMapper.addUserInfo(userInfo);
    }

    /**
     * 登录功能
     */
    @Override
    public String login(User user) throws Exception{
        String phone = user.getPhone() == null ? "" : user.getPhone();
        String email = user.getEmail() == null ? "" : user.getEmail();
        if (StringUtils.isNullOrEmpty(phone) && StringUtils.isNullOrEmpty(email)) {
            throw new CustomException("账号不能为空");
        }
        // 1. 查询用户是否存在
        User userInDb = userMapper.getUserByPhoneOrEmail(phone, email);
        if(userInDb == null){
            throw new CustomException("用户不存在");
        }
        // 2. 用户存在，获取密码，对密码进行RSA解密
        String password = user.getPassword();
        String rawPassword = "";
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
//            e.printStackTrace();
            throw new CustomException("密码RSA解密失败");
        }
        // 3. 从数据库获取盐值，对密码进行MD5加密
        String salt = userInDb.getSalt();
        String md5Password = MD5Util.sign(rawPassword, salt, "UTF-8");

        // 4. 查询数据库中的密码，判断密码是否正确
        if(!md5Password.equals(userInDb.getPassword())) {
            throw new CustomException("密码错误");
        }
        // 5. 密码正确，JWT生成token
        return TokenUtil.generateToken(userInDb.getId());
    }

    /**
     * 根据userId获取用户信息
     */
    @Override
    public UserVO getUserInfoById(Long userId){
        User user = userMapper.getUserById(userId);
        UserInfo userInfo = userMapper.getUserInfoById(userId);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setUserInfo(userInfo);
        return userVO;
    }

    /**
     * 更新用户邮箱以及密码
     */
    @Override
    public void updateUser(User user){
        // 1. 获取用户id，根据id查询用户是否存在
        Long id = user.getId();
        User userIndb = userMapper.getUserById(id);
        if(userIndb == null) {
            throw new CustomException("用户不存在");
        }
        if(!user.getPhone().equals(userIndb.getPhone())) {
            throw new CustomException("手机号填写错误");
        }
        // 2. 用户存在，判断手机号邮箱密码是否为空
        if(StringUtils.isNullOrEmpty(user.getEmail() )
                || StringUtils.isNullOrEmpty(user.getPassword())) {
            throw new CustomException("表单参数不能为空");
        }
        // 邮箱已经在user里了，不用处理，只需要处理密码即可
        // 3. 对密码进行RSA解密，并MD5加密
        String password = user.getPassword();
        String rawPassword = null;
        try {
            rawPassword = RSAUtil.decrypt(password);
        } catch (Exception e) {
            throw new CustomException("密码RSA解析失败");
        }
        String md5Password = MD5Util.sign(rawPassword, userIndb.getSalt(), "UTF-8");
        user.setPassword(md5Password);
        user.setUpdateTime(new Date());
        userMapper.updateUser(user);
    }

    /**
     * 更新用户基本信息
     */
    @Override
    public void updateUserInfo(UserInfo userInfo){
        // 1. 获取用户id，根据id查询用户是否存在
        Long userId = userInfo.getUserId();
        UserInfo userInfoIndb = userMapper.getUserInfoById(userId);
        if(userInfoIndb == null) {
            throw new CustomException("用户不存在");
        }
        userInfo.setUpdateTime(new Date());
        userMapper.updateUserInfo(userInfo);
    }

    @Override
    public User getUserById(String userId){
        return userMapper.getUserById(Long.valueOf(userId));
    }

}
