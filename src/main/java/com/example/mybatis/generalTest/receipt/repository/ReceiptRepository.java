package com.example.mybatis.generalTest.receipt.repository;

import com.example.mybatis.generalTest.receipt.entity.Receipt;
import com.example.mybatis.generalTest.receipt.entity.ReceiptEntityDto;
import com.example.mybatis.generalTest.receipt.enums.ReceiptStatus;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import org.apache.ibatis.annotations.Arg;
import org.apache.ibatis.annotations.ConstructorArgs;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@Mapper
public interface ReceiptRepository {

    void create(ReceiptEntityDto receipt);

    void update(ReceiptEntityDto receipt);

    void delete(ReceiptId id);

    ReceiptEntityDto findOne(ReceiptId id);

}
