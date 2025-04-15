package com.example.mybatis.generalTest.receipt.service;

import com.example.mybatis.generalTest.receipt.dto.ReceiptCreateDto;
import com.example.mybatis.generalTest.receipt.dto.ReceiptServiceDto;
import com.example.mybatis.generalTest.receipt.vo.ReceiptContents;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;

public interface ReceiptService {

    void init(ReceiptCreateDto createDto);

    void update(ReceiptId id, ReceiptServiceDto dto);

    void cancel(ReceiptId id);

    void submit(ReceiptId id);

}
