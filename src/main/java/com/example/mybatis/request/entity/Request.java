package com.example.mybatis.request.entity;

import com.example.mybatis.request.vo.RequestContents;
import com.example.mybatis.request.vo.RequestId;

public interface Request {

    RequestId id();
    RequestContents contents();

    void submit();
    void cancel();
    void updateFrom(RequestContents contents);

    static Request createFrom(RequestContents contents) {
        return null;
    }
}
