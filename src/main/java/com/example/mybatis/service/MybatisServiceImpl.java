package com.example.mybatis.service;

import com.example.mybatis.dto.TestCondition;
import com.example.mybatis.entity.Test;
import com.example.mybatis.repository.MybatisRepository;
import com.example.mybatis.vo.TestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MybatisServiceImpl implements MybatisService{

    private final MybatisRepository repository;

    @Override
    public List<TestVo> get(TestCondition condition) {
        return repository.get(condition);
    }

    @Override
    public void create(Test test) {
        repository.create(test);

        throw new RuntimeException();
    }
}
