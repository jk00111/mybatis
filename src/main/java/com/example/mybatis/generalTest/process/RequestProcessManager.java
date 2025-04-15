package com.example.mybatis.generalTest.process;

import com.example.mybatis.generalTest.receipt.dto.ReceiptCreateDto;
import com.example.mybatis.generalTest.receipt.service.ReceiptService;
import com.example.mybatis.generalTest.request.entity.Request;
import com.example.mybatis.generalTest.request.vo.RequestId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestProcessManager implements ProcessManager<Request> {

    private final ReceiptService receiptService;

    @Override
    public void proceed(Request request) {
        RequestId requestId = request.id();
        receiptService.init(new ReceiptCreateDto(requestId));
    }
}
