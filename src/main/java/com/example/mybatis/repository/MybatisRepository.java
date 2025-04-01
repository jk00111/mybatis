package com.example.mybatis.repository;

import com.example.mybatis.dto.TestCondition;
import com.example.mybatis.entity.Test;
import com.example.mybatis.vo.TestVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MybatisRepository {

    List<TestVo> get(TestCondition condition);
    void create(Test test);
}
