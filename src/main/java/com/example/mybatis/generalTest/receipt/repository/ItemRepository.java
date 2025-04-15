package com.example.mybatis.generalTest.receipt.repository;

import com.example.mybatis.generalTest.receipt.vo.Item;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ItemRepository {

    void create(Item item);

    void update(Item item);

    void delete(Item item);

    List<Item> findByReceipt(ReceiptId id);
}
