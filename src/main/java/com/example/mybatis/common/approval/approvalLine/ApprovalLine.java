package com.example.mybatis.common.approval.approvalLine;

import com.example.mybatis.common.approval.user.ApprovalDecider;

import java.util.List;

public interface ApprovalLine {

    void add(ApprovalDecider user);

    void remove(ApprovalDecider user);

    List<ApprovalDecider> getAll();

    ApprovalDecider findDeciderInLine(ApprovalDecider user);

    boolean isFinish();

    boolean isRejected();

}
