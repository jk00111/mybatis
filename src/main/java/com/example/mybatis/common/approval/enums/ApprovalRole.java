package com.example.mybatis.common.approval.enums;

import com.example.mybatis.common.config.typeHandler.Enumerable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApprovalRole implements Enumerable {

    REQUESTER,

    APPROVER,

    ;

    @Override
    public String value() {
        return name();
    }

}
