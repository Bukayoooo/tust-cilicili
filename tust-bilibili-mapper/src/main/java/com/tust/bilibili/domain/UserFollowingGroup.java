package com.tust.bilibili.domain;

import lombok.Data;

import java.util.Date;

/**
 * 用户关注分组
 * CREATE TABLE `t_following_group` (
 *   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
 *   `userId` bigint DEFAULT NULL COMMENT '用户id',
 *   `name` varchar(50) DEFAULT NULL COMMENT '关注分组名称',
 *   `type` varchar(5) DEFAULT NULL COMMENT '关注分组类型：0特别关注  1悄悄关注 2默认分组  3用户自定义分组',
 *   `createTime` datetime DEFAULT NULL COMMENT '创建时间',
 *   `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户关注分组表';
 */
@Data
public class UserFollowingGroup{

    private Long id;

    private Long userId;

    private String name;

    private String type;

    private Date createTime;

    private Date updateTime;

}
