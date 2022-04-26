package com.tust.bilibili.service;

import com.tust.bilibili.mapper.DemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DemoService {

    @Autowired
    public DemoMapper demoMapper;

    public Map<String, Object> query(Long id) {
        return demoMapper.query(id);
    }

}
