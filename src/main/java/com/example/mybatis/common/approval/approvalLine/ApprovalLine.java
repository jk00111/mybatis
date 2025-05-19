package com.example.mybatis.common.approval.approvalLine;


import com.example.mybatis.common.approval.entity.ApprovalDecider;

public interface ApprovalLine extends Iterable<ApprovalDecider> {

    void add(ApprovalDecider user);

    void remove(ApprovalDecider user);

    ApprovalDecider findDecider(ApprovalDecider user);

    boolean isFinish();

    boolean isRejected();

}
