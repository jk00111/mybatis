package com.example.mybatis.generalTest.request.service.impl;

import com.example.mybatis.generalTest.process.ProcessManager;
import com.example.mybatis.generalTest.request.entity.Request;
import com.example.mybatis.generalTest.request.vo.RequestContents;
import com.example.mybatis.generalTest.request.repository.RequestRepository;
import com.example.mybatis.generalTest.request.service.RequestService;
import com.example.mybatis.generalTest.request.vo.RequestId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final ProcessManager<Request> processManager;

    @Override
    public void write(RequestContents contents) {
        Request request = Request.createFrom(contents);
        requestRepository.create(request);
    }

    @Override
    public void update(RequestId id, RequestContents contents) {
        Request request = requestRepository.findOne(id);
        request.updateContents(contents);
        requestRepository.update(request);
    }

    @Override
    public void submit(RequestId id) {
        Request request = requestRepository.findOne(id);
        request.submit();
        requestRepository.update(request);
        processManager.proceed(request);
    }

    @Override
    public void cancel(RequestId id) {
        Request request = requestRepository.findOne(id);
        request.cancel();
        requestRepository.update(request);
    }
}
