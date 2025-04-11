package com.example.mybatis.generalTest.receipt.dto;

import com.example.mybatis.generalTest.request.vo.RequestId;
import lombok.Getter;

@Getter
public class ReceiptCreateDto {

    private final RequestId requestId;

    public ReceiptCreateDto(RequestId requestId) {
        this.requestId = requestId;
    }

    public RequestId requestId() {
        return requestId;
    }

}
