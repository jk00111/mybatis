package com.example.mybatis.generalTest.request.service;

import com.example.mybatis.generalTest.request.dto.RequestCondition;
import com.example.mybatis.generalTest.request.dto.RequestResponseDto;
import com.example.mybatis.generalTest.request.vo.RequestId;

import java.util.List;

public interface RequestQueryService {

    List<RequestResponseDto> findAll(RequestCondition condition);

    RequestResponseDto findOne(RequestId id);

}
