package com.example.mybatis.generalTest.request.service.impl;

import com.example.mybatis.generalTest.request.dto.RequestCondition;
import com.example.mybatis.generalTest.request.dto.RequestResponseDto;
import com.example.mybatis.generalTest.request.repository.RequestQueryRepository;
import com.example.mybatis.generalTest.request.service.RequestQueryService;
import com.example.mybatis.generalTest.request.vo.RequestId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestQueryServiceImpl implements RequestQueryService {

    private final RequestQueryRepository requestQueryRepository;

    @Override
    public List<RequestResponseDto> findAll(RequestCondition condition) {
        return requestQueryRepository.findAll(condition);
    }

    @Override
    public RequestResponseDto findOne(RequestId id) {
        return requestQueryRepository.findOne(id);
    }
}
