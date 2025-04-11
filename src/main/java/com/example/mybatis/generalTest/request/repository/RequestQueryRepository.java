package com.example.mybatis.generalTest.request.repository;

import com.example.mybatis.generalTest.request.dto.RequestCondition;
import com.example.mybatis.generalTest.request.dto.RequestResponseDto;
import com.example.mybatis.generalTest.request.vo.RequestId;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RequestQueryRepository {

    RequestResponseDto findOne(RequestId id);

    List<RequestResponseDto> findAll(RequestCondition condition);

}
