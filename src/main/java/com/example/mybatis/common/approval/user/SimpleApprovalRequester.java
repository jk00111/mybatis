package com.example.mybatis.common.approval.user;

import com.example.mybatis.common.approval.enums.ApprovalAction;
import com.example.mybatis.common.approval.enums.ApprovalRole;
import lombok.EqualsAndHashCode;

public class SimpleApprovalRequester implements ApprovalRequester {

    @EqualsAndHashCode.Include
    private Integer approvalId;

    @EqualsAndHashCode.Include
    private Integer userId;
    private String name;
    private ApprovalRole role;
    private ApprovalAction action;

    public SimpleApprovalRequester(Integer userId, String name, ApprovalRole role, ApprovalAction action) {
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.action = action;
    }

    @Override
    public Integer id() {
        return userId;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public ApprovalRole role() {
        return role;
    }

    @Override
    public ApprovalAction action() {
        return action;
    }

    @Override
    public void cancel() {
        this.action = ApprovalAction.CANCEL;
    }
}
