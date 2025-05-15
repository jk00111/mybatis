package com.example.mybatis.common.approval.enums;

import com.example.mybatis.common.config.typeHandler.Enumerable;

public enum ApprovalStatus implements Enumerable {

    ESCALATED,

    CANCELED,

    APPROVED,

    REJECTED,
    ;


    @Override
    public String value() {
        return name();
    }
}
