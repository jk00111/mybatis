package com.example.mybatis.vo;

import com.example.mybatis.enums.UseType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestVo {

    private Integer id;
    private String name;
    private UseType useYn;
}
