package com.example.mybatis.generalTest.receipt.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Item {

    private final Integer receiptId;
    private final String itemCode;
    private final String name;

    public Item(Integer receiptId, String itemCode, String name) {
        this.receiptId = receiptId;
        this.itemCode = itemCode;
        this.name = name;
    }
}
