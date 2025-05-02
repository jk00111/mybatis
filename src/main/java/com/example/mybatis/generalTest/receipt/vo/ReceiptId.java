package com.example.mybatis.generalTest.receipt.vo;

import com.example.mybatis.common.IdentityValue;

public class ReceiptId implements IdentityValue {

    private final Integer id;

    public ReceiptId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer get() {
        return id;
    }
}
