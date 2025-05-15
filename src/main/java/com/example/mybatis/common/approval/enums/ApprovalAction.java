package com.example.mybatis.common.approval.enums;

import com.example.mybatis.common.config.typeHandler.Enumerable;

public enum ApprovalAction implements Enumerable {

    APPROVE,

    REJECT,

    NONE,
    ;


    @Override
    public String value() {
        return name();
    }
}
