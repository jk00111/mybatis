package com.example.mybatis.generalTest.request.vo;

public class RequestId {

    private final Integer id;

    public RequestId(Integer id) {
        if (id == null) {
            throw new NullPointerException();
        }

        this.id = id;
    }

    public Integer get() {
        return id;
    }
}
