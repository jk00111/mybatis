package com.example.mybatis.generalTest.request.repository;

import com.example.mybatis.generalTest.request.entity.Request;
import com.example.mybatis.generalTest.request.vo.RequestId;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RequestRepository {

    void create(Request entity);
    void update(Request entity);
    void delete(RequestId id);
    Request findOne(RequestId id);

}
