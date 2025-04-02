package com.example.mybatis.request.repository;

import com.example.mybatis.request.entity.Request;
import com.example.mybatis.request.vo.RequestId;

public interface RequestRepository {

    void create(Request entity);
    void update(Request entity);
    void delete(RequestId id);
    Request findOne(RequestId id);

}
