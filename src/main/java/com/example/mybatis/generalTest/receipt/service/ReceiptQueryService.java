package com.example.mybatis.generalTest.receipt.service;

import com.example.mybatis.generalTest.receipt.dto.ReceiptCondition;
import com.example.mybatis.generalTest.receipt.dto.ReceiptResponseDto;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;

import java.util.List;

public interface ReceiptQueryService {

    List<ReceiptResponseDto> findAll(ReceiptCondition condition);

    ReceiptResponseDto findOne(ReceiptId id);

}
