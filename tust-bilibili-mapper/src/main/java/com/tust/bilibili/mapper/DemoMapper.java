package com.tust.bilibili.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface DemoMapper{

    public Map<String, Object> query(Long id);
}
