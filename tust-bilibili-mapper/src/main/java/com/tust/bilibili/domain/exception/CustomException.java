package com.tust.bilibili.domain.exception;

import lombok.Data;

/**
 * 定制错误信息
 */
@Data
public class CustomException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String code;

    public CustomException(String code, String name) {
        super(name);
        this.code = code;
    }

    public CustomException(String name) {
        super(name);
        this.code = "500";
    }

}
