package com.example.mybatis.common.approval.user;

import com.example.mybatis.common.approval.enums.ApprovalAction;
import com.example.mybatis.common.approval.enums.ApprovalRole;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ApprovalRequester implements ApprovalUser {

    @EqualsAndHashCode.Include
    private Integer approvalId;

    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private ApprovalRole role;
    private ApprovalAction action;

    public ApprovalRequester(Integer id, String name, ApprovalRole role, ApprovalAction action) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.action = action;
    }

    @Override
    public Integer id() {
        return id;
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

    public void cancel() {
        this.action = ApprovalAction.CANCEL;
    }
}
