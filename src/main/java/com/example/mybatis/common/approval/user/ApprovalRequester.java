package com.example.mybatis.common.approval.user;

import com.example.mybatis.common.approval.enums.ApprovalAction;
import com.example.mybatis.common.approval.enums.ApprovalRole;
import com.example.mybatis.common.entity.User;

public interface ApprovalRequester {

    Integer id();

    String name();

    ApprovalRole role();

    ApprovalAction action();

    void cancel();

    static ApprovalRequester from(User user) {
        return new SimpleApprovalRequester(user.getId(), user.getName(), ApprovalRole.REQUESTER, ApprovalAction.ESCALATE);
    }
}
