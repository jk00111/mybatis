package com.example.mybatis.common.approval.vo;

import com.example.mybatis.common.entity.User;

public class ApprovalSubmit {

    private final ApprovalId approvalId;
    private final ApprovalUser submitUser;

    private ApprovalSubmit(ApprovalId approvalId, ApprovalUser submitUser) {
        this.approvalId = approvalId;
        this.submitUser = submitUser;
    }

    public static ApprovalSubmit ofApprove(ApprovalId id, User user) {
        return new ApprovalSubmit(id, ApprovalUser.of(user));
    }

    public static ApprovalSubmit ofReject(ApprovalId id, User user) {
        return new ApprovalSubmit(id, ApprovalUser.of(user));
    }

    public ApprovalId approvalId() {
        return approvalId;
    }

    public ApprovalUser submitUser() {
        return submitUser;
    }

}
