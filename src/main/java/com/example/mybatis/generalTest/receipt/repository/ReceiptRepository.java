package com.example.mybatis.generalTest.receipt.repository;

import com.example.mybatis.generalTest.receipt.entity.Receipt;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;

public interface ReceiptRepository {

    void create(Receipt receipt);

    void update(Receipt receipt);

    void delete(ReceiptId id);

    Receipt findOne(ReceiptId id);

}
