package com.example.mybatis.request.vo;

import com.example.mybatis.common.enums.TestType;

import java.time.LocalDateTime;

public interface RequestContents {

    String title();

    LocalDateTime requestDate();

    TestType type();

    Requester requester();

}
