package com.example.mybatis.controller;

import com.example.mybatis.service.MybatisService;
import com.example.mybatis.vo.Test;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/mybatis")
@RequiredArgsConstructor
public class MybatisController {

    private final MybatisService service;

    @GetMapping
    public ResponseEntity<List<Test>> test() {
        List<Test> tests = service.get();
        return ResponseEntity.ok(tests);
    }
}
