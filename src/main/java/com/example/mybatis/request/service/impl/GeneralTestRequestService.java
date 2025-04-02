package com.example.mybatis.request.service.impl;

import com.example.mybatis.request.vo.RequestContents;
import com.example.mybatis.request.entity.Request;
import com.example.mybatis.request.repository.RequestRepository;
import com.example.mybatis.request.service.RequestService;
import com.example.mybatis.request.vo.RequestId;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GeneralTestRequestService implements RequestService {

    private final RequestRepository requestRepository;

    @Override
    public void write(RequestContents contents) {
        Request request = Request.createFrom(contents);
        requestRepository.create(request);
    }

    @Override
    public void update(RequestId id, RequestContents contents) {
        Request request = requestRepository.findOne(id);
        request.updateFrom(contents);
        requestRepository.update(request);
    }

    @Override
    public void submit(RequestId id) {
        Request request = requestRepository.findOne(id);
        request.submit();
        requestRepository.update(request);
    }

    @Override
    public void cancel(RequestId id) {
        Request request = requestRepository.findOne(id);
        request.cancel();
        requestRepository.update(request);
    }
}
