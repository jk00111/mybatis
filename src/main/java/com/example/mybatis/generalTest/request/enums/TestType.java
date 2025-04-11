package com.example.mybatis.generalTest.request.enums;

import com.example.mybatis.common.config.typeHandler.Enumerable;

public enum TestType implements Enumerable {

    GENERAL,
    BATCH,
    ;


    @Override
    public String value() {
        return name();
    }
}
