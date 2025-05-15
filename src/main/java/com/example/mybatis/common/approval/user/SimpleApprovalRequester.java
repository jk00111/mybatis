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

    public SimpleApprovalRequester(Integer userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    @Override
    public Integer id() {
        return userId;
    }

    @Override
    public String name() {
        return name;
    }
}
