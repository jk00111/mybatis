package com.example.mybatis.generalTest.receipt.repository;

import com.example.mybatis.generalTest.receipt.dto.ReceiptCondition;
import com.example.mybatis.generalTest.receipt.dto.ReceiptResponseDto;
import com.example.mybatis.generalTest.receipt.vo.Item;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReceiptQueryRepository {

    ReceiptResponseDto findOne(ReceiptId id);

    List<ReceiptResponseDto> findAll(ReceiptCondition condition);

    List<Item> findItems(ReceiptId receiptId);
}
