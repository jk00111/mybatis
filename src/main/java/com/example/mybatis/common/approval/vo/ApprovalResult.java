package com.example.mybatis.common.approval.vo;

import com.example.mybatis.common.approval.entity.ApprovalEntity;

public class ApprovalResult {

    private final ApprovalId id;
    private final boolean finish;

    public ApprovalResult(ApprovalEntity entity) {
        this.id = entity.getId();
        this.finish = entity.isFinish();
    }

    public ApprovalId id() {
        return id;
    }
    public boolean isFinish() {
        return finish;
    }
}
