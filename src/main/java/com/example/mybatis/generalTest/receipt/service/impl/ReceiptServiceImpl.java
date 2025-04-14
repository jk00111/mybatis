package com.example.mybatis.generalTest.receipt.service.impl;

import com.example.mybatis.generalTest.receipt.dto.ReceiptCreateDto;
import com.example.mybatis.generalTest.receipt.dto.ReceiptServiceDto;
import com.example.mybatis.generalTest.receipt.entity.Receipt;
import com.example.mybatis.generalTest.receipt.repository.ReceiptRepository;
import com.example.mybatis.generalTest.receipt.service.ReceiptService;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Override
    public void init(ReceiptCreateDto createDto) {
        Receipt receipt = Receipt.ofCreate(createDto);
        receiptRepository.create(receipt);
    }

    @Override
    public void update(ReceiptId id, ReceiptServiceDto dto) {
        Receipt receipt = receiptRepository.findOne(id);
        receipt.updateFrom(dto);

        receiptRepository.update(receipt);
    }

    @Override
    public void cancel(ReceiptId id) {
        Receipt receipt = receiptRepository.findOne(id);
        receipt.cancel();
        receiptRepository.update(receipt);
    }

    @Override
    public void submit(ReceiptId id) {
        Receipt receipt = receiptRepository.findOne(id);
        receipt.submit();
        receiptRepository.update(receipt);
    }
}
