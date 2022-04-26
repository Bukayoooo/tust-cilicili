package com.tust.bilibili.domain;

import lombok.Data;

import java.util.Date;

/**
 *
 Create Table

 CREATE TABLE `t_user` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
 `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
 `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
 `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
 `salt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '盐值',
 `createTime` datetime DEFAULT NULL COMMENT '创建时间',
 `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表'

 */

@Data
public class User{

    private Long id;

    private String phone;

    private String email;

    private String password;

    private String salt;

    private Date createTime;

    private Date updateTime;

    private String nickName;

}
