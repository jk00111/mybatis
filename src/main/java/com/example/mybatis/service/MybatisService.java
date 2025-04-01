package com.example.mybatis.service;

import com.example.mybatis.dto.TestCondition;
import com.example.mybatis.entity.Test;
import com.example.mybatis.vo.TestVo;

import java.util.List;

public interface MybatisService {

    List<TestVo> get(TestCondition condition);
    void create(Test test);

}
