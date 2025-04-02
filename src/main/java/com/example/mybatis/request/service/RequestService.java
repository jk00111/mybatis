package com.example.mybatis.request.service;

import com.example.mybatis.request.vo.RequestContents;
import com.example.mybatis.request.vo.RequestId;

public interface RequestService {

    void write(RequestContents contents);

    void update(RequestId id, RequestContents contents);

    void submit(RequestId id);

    void cancel(RequestId id);

}
