package com.example.mybatis.generalTest.request.service;

import com.example.mybatis.generalTest.request.vo.RequestContents;
import com.example.mybatis.generalTest.request.vo.RequestId;

public interface RequestService {

    void write(RequestContents contents);

    void update(RequestId id, RequestContents contents);

    void submit(RequestId id);

    void cancel(RequestId id);

}
