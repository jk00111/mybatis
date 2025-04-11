package com.example.mybatis.generalTest.request.enums;

import com.example.mybatis.common.config.typeHandler.Enumerable;

public enum RequestStatus implements Enumerable {

    INIT,
    SUBMIT,
    CANCEL,
    ;


    @Override
    public String value() {
        return name();
    }
}
