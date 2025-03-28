package com.example.mybatis.service;

import com.example.mybatis.dto.TestCondition;
import com.example.mybatis.repository.MybatisRepository;
import com.example.mybatis.vo.Test;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MybatisServiceImpl implements MybatisService{

    private final MybatisRepository repository;

    @Override
    public List<Test> get(TestCondition condition) {
        return repository.get(condition);
    }
}
