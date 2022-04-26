package com.tust.bilibili.mapper;

import com.tust.bilibili.domain.UserFollowing;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFollowingMapper {

    void addUserFollowing(UserFollowing userFollowing);

    void deleteUserFollowing(Long userId, String followingId);
}
