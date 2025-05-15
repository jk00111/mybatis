package com.example.mybatis.common.approval.vo;

import com.example.mybatis.common.approval.user.ApprovalDecider;
import com.example.mybatis.common.entity.User;

public class ApprovalSubmit {

    private final ApprovalId approvalId;
    private final ApprovalDecider submitUser;

    private ApprovalSubmit(ApprovalId approvalId, ApprovalDecider submitUser) {
        this.approvalId = approvalId;
        this.submitUser = submitUser;
    }

    public static ApprovalSubmit of(ApprovalId id, User user) {
        return new ApprovalSubmit(id, ApprovalDecider.of(user, id.getId()));
    }


    public ApprovalId approvalId() {
        return approvalId;
    }

    public ApprovalDecider submitUser() {
        return submitUser;
    }

}
