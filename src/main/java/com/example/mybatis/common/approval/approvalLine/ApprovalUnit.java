package com.example.mybatis.common.approval.approvalLine;

import com.example.mybatis.common.approval.vo.ApprovalUnitId;
import com.example.mybatis.common.approval.vo.ApprovalUser;

import java.util.ArrayList;
import java.util.List;

public interface ApprovalUnit {

    void approve(ApprovalUser user);

    void reject(ApprovalUser user);

    ApprovalUnit get(ApprovalUser user);

    boolean contains(ApprovalUser user);

    boolean isFinish();

    boolean isUpdated();

    static ApprovalUnit makeLine(List<ApprovalUser> line) {
        List<ApprovalUnit> approvers = new ArrayList<>();

        for (ApprovalUser approver : line) {
            approvers.add(makeStep(approver));
        }

        return new ApprovalLine(approvers);
    }


    private static ApprovalUnit makeStep(ApprovalUser user) {
        return new ApprovalStep(user);
    }
}
