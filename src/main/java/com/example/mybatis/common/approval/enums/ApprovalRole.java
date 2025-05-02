package com.example.mybatis.common.approval.enums;

import com.example.mybatis.common.config.typeHandler.Enumerable;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ApprovalRole implements Enumerable {

    APPROVER(ApprovalDecision.APPROVE, ApprovalDecision.REJECT),

    REVIEWER(ApprovalDecision.REVIEW, ApprovalDecision.REJECT),

    ;


    private final ApprovalDecision next;

    private final ApprovalDecision prev;


    @Override
    public String value() {
        return name();
    }


    public ApprovalDecision next() {
        return this.next;
    }

    public ApprovalDecision prev() {
        return this.prev;
    }
}
