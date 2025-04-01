package com.example.mybatis.entity;

import com.example.mybatis.enums.UseType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Test {

    private Integer id;
    private String name;
    private UseType useYn;



    public static Test ofCreate(String name) {
        return Test.builder()
                .name(name)
                .useYn(UseType.YES)
                .build();
    }
}
