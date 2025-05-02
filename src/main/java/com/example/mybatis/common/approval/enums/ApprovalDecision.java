package com.example.mybatis.common.approval.enums;

import com.example.mybatis.common.config.typeHandler.Enumerable;

public enum ApprovalDecision implements Enumerable {


    APPROVE,

    REVIEW,

    REJECT,

    NOT,
    ;


    @Override
    public String value() {
        return name();
    }
}
