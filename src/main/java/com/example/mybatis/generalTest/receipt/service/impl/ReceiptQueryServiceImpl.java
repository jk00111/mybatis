package com.example.mybatis.generalTest.receipt.service.impl;

import com.example.mybatis.generalTest.receipt.dto.ReceiptCondition;
import com.example.mybatis.generalTest.receipt.dto.ReceiptResponseDto;
import com.example.mybatis.generalTest.receipt.repository.ReceiptQueryRepository;
import com.example.mybatis.generalTest.receipt.service.ReceiptQueryService;
import com.example.mybatis.generalTest.receipt.vo.Item;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReceiptQueryServiceImpl implements ReceiptQueryService {

    private final ReceiptQueryRepository queryRepository;

    @Override
    public List<ReceiptResponseDto> findAll(ReceiptCondition condition) {
        return queryRepository.findAll(condition);
    }

    @Override
    public ReceiptResponseDto findOne(ReceiptId id) {
        ReceiptResponseDto dto = queryRepository.findOne(id);
        List<Item> items = queryRepository.findItems(id);

        dto.setItems(items);

        return dto;
    }
}
