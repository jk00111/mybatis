package com.example.mybatis.common.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {

    private final Integer id;
    private final String name;

    
}
