package com.example.mybatis.service;

import com.example.mybatis.dto.TestCondition;
import com.example.mybatis.vo.Test;

import java.util.List;

public interface MybatisService {

    List<Test> get(TestCondition condition);

}
