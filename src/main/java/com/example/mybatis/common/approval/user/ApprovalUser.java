package com.example.mybatis.common.approval.user;

import com.example.mybatis.common.approval.enums.ApprovalAction;
import com.example.mybatis.common.approval.enums.ApprovalRole;
import com.example.mybatis.common.entity.User;

public interface ApprovalUser {

    Integer id();

    String name();

    ApprovalRole role();

    ApprovalAction action();

    static ApprovalUser requestFrom(User user) {
        return new ApprovalRequester(user.getId(), user.getName(), ApprovalRole.REQUESTER, ApprovalAction.ESCALATE);
    }

    static ApprovalDecider fromEscalate(User user) {
        return new OrderlessApprover(user.getId(), user.getName(), ApprovalRole.APPROVER, ApprovalAction.NONE);
    }

    static ApprovalDecider deciderOf(User user, Integer approvalId) {
        return new OrderlessApprover(approvalId, user.getId(), user.getName(), ApprovalRole.APPROVER, ApprovalAction.NONE);
    }
}
