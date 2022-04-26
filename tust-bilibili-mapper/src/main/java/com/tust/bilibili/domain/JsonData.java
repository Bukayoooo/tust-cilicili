package com.tust.bilibili.domain;

import lombok.Data;

/**
 * 统一接口返回协议
 */
@Data
public class JsonData<T> {

    // 状态码
    private String code;
    // 返回信息
    private String msg;
    // 返回的数据
    private T data;

    // 成功，往前端返回数据
    public JsonData(T data){
        code = "0";
        msg = "成功";
        this.data = data;
    }
    // 失败，往前端返回错误信息
    public JsonData(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    // 静态方法专门用来往前端返回字符串信息
    public static JsonData<String> success() {
        return new JsonData<>(null);
    }

    public static JsonData<String> success(String data) {
        return new JsonData<>(data);
    }

    public static JsonData<String> fail() {
        return new JsonData<>("1", "服务器内部出现错误，访问失败");
    }

    public static JsonData<String> fail(String code, String msg) {
        return new JsonData<>(code, msg);
    }

}
