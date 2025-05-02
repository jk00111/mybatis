package com.example.mybatis.common.approval.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalResult {

    private final ApprovalId id;
    private final boolean finish;

    public ApprovalId id() {
        return id;
    }
    public boolean isFinish() {
        return finish;
    }
}
