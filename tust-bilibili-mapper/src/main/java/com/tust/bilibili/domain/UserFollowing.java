package com.tust.bilibili.domain;

import lombok.Data;

import java.util.Date;

/**
 * 用户关注类
 * CREATE TABLE `t_user_following` (
 *   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 *   `userId` bigint DEFAULT NULL COMMENT '用户id',
 *   `followingId` int DEFAULT NULL COMMENT '关注用户id',
 *   `groupId` int DEFAULT NULL COMMENT '关注分组id',
 *   `createTime` datetime DEFAULT NULL COMMENT '创建时间',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户关注表';
 */
@Data
public class UserFollowing{

    private Long id;

    private Long userId;

    private String followingId;

    private String groupId;

    private Date createTime;

}
