package com.example.mybatis.controller;

import com.example.mybatis.dto.TestCondition;
import com.example.mybatis.dto.TestDto;
import com.example.mybatis.entity.Test;
import com.example.mybatis.enums.UseType;
import com.example.mybatis.service.MybatisService;
import com.example.mybatis.vo.TestVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/mybatis")
@RequiredArgsConstructor
public class MybatisController {

    private final MybatisService service;

    @GetMapping("/{useYn}")
    public ResponseEntity<List<TestVo>> test(@PathVariable UseType useYn) {
        TestCondition testCondition = new TestCondition(useYn);

        List<TestVo> testVos = service.get(testCondition);
        return ResponseEntity.ok(testVos);
    }


    @PostMapping
    public ResponseEntity<String> save(@RequestBody TestDto dto) {
        service.create(Test.ofCreate(dto.getName()));
        return ResponseEntity.ok("ok");
    }
}
