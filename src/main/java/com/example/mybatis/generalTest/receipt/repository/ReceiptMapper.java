package com.example.mybatis.generalTest.receipt.repository;

import com.example.mybatis.generalTest.receipt.entity.ReceiptEntityDto;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface ReceiptMapper {

    void create(ReceiptEntityDto dto);

    void update(ReceiptEntityDto dto);

    void delete(ReceiptId id);

    ReceiptEntityDto findOne(ReceiptId id);

}
