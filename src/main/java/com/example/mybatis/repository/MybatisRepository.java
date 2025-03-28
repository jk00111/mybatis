package com.example.mybatis.repository;

import com.example.mybatis.dto.TestCondition;
import com.example.mybatis.vo.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MybatisRepository {

    List<Test> get(TestCondition condition);
}
