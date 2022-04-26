package com.tust.bilibili.handler;

import com.tust.bilibili.domain.JsonData;
import com.tust.bilibili.domain.exception.CustomException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)  // 设置为最高优先级的处理器
public class CommonGlobalExceptionHandler{

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData<String> commonGlobalExceptionHandler(HttpServletRequest request, Exception e) {
        String message = e.getMessage();
        if(e instanceof CustomException) {
            String errorCode = ((CustomException) e).getCode();
            return JsonData.fail(errorCode, message);
        }else {
            return JsonData.fail("500", message);
        }
    }
}
