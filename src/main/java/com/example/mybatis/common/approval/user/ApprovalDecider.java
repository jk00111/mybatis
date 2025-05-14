package com.example.mybatis.common.approval.user;

public interface ApprovalDecider extends ApprovalUser {

    void register(Integer approvalId);

    void approve();

    void reject();

    boolean isUpdated();
}
