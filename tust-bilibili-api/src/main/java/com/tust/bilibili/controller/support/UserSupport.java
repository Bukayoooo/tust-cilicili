package com.tust.bilibili.controller.support;

import com.tust.bilibili.domain.exception.CustomException;
import com.tust.bilibili.utils.TokenUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户功能相关工具
 */
@Controller
public class UserSupport {

    /**
     * 根据token获取userId
     */
    public Long getUserIdByToken() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 根据请求头获取token
        String token = request.getHeader("token");
        Long userId = TokenUtil.verifyToken(token);
        if(userId < 0) {
            throw new CustomException("非法用户");
        }
        return userId;
    }
}
