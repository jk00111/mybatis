package com.example.mybatis.controller;

import com.example.mybatis.dto.TestCondition;
import com.example.mybatis.enums.UseType;
import com.example.mybatis.service.MybatisService;
import com.example.mybatis.vo.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/mybatis")
@RequiredArgsConstructor
public class MybatisController {

    private final MybatisService service;

    @GetMapping("/{useYn}")
    public ResponseEntity<List<Test>> test(@PathVariable UseType useYn) {
        TestCondition testCondition = new TestCondition(useYn);

        List<Test> tests = service.get(testCondition);
        return ResponseEntity.ok(tests);
    }
}
