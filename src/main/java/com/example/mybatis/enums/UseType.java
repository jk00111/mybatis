package com.example.mybatis.enums;

import com.example.mybatis.config.typeHandler.Enumerable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UseType implements Enumerable {

    YES("Y"),

    NO("N"),
    ;

    private final String value;


    @Override
    public String value() {
        return value;
    }
}
