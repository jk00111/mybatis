package com.example.mybatis.common.approval.user;

import com.example.mybatis.common.approval.enums.ApprovalAction;
import com.example.mybatis.common.approval.enums.ApprovalRole;
import com.example.mybatis.common.entity.User;

public interface ApprovalDecider {

    Integer id();

    String name();

    ApprovalRole role();

    ApprovalAction action();

    void register(Integer approvalId);

    void approve();

    void reject();

    boolean isUpdated();

    static ApprovalDecider fromEscalate(User user) {
        return new OrderlessApprover(user.getId(), user.getName(), ApprovalRole.APPROVER, ApprovalAction.NONE);
    }

    static ApprovalDecider of(User user, Integer approvalId) {
        return new OrderlessApprover(approvalId, user.getId());
    }
}
