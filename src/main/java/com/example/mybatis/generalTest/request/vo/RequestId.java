package com.example.mybatis.generalTest.request.vo;

import com.example.mybatis.common.IdentityValue;

public class RequestId implements IdentityValue {

    private final Integer id;

    public RequestId(Integer id) {
        if (id == null) {
            throw new NullPointerException();
        }

        this.id = id;
    }

    @Override
    public Integer get() {
        return id;
    }
}
