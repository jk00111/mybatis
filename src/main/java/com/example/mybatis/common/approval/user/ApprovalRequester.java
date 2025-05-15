package com.example.mybatis.common.approval.user;

import com.example.mybatis.common.entity.User;

public interface ApprovalRequester {

    Integer id();

    String name();

    static ApprovalRequester from(User user) {
        return new SimpleApprovalRequester(user.getId(), user.getName());
    }
}
