package com.tust.bilibili.domain;

import lombok.Data;

import java.util.Date;

/**

 CREATE TABLE `t_user_info` (
 `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
 `userId` bigint DEFAULT NULL COMMENT '用户id',
 `nick` varchar(100) DEFAULT NULL COMMENT '昵称',
 `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
 `sign` text COMMENT '签名',
 `gender` varchar(2) DEFAULT NULL COMMENT '性别：0男 1女 2未知',
 `birth` varchar(20) DEFAULT NULL COMMENT '生日',
 `createTime` datetime DEFAULT NULL COMMENT '创建时间',
 `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
 PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户基本信息表'

 */
@Data
public class UserInfo{

    private Long id;

    private Long userId;

    private String nick;

    private String avatar;

    private String sign;

    private String birth;

    private String gender;

    private Date createTime;

    private Date updateTime;
}
