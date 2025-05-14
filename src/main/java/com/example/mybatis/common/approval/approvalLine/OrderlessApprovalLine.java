package com.example.mybatis.common.approval.approvalLine;

import com.example.mybatis.common.approval.enums.ApprovalAction;
import com.example.mybatis.common.approval.user.ApprovalDecider;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class OrderlessApprovalLine implements ApprovalLine {

    private final List<ApprovalDecider> line;

    @Override
    public boolean isFinish() {
        for (ApprovalDecider decider : line) {
            if (ApprovalAction.NONE.equals(decider.action())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public void add(ApprovalDecider user) {
        line.add(user);
    }

    @Override
    public void remove(ApprovalDecider user) {
        line.remove(user);
    }

    @Override
    public List<ApprovalDecider> findAll() {
        return line;
    }

    @Override
    public ApprovalDecider findApprovalDecider(ApprovalDecider user) {
        for (ApprovalDecider approvalDecider : line) {
            if (approvalDecider.equals(user)) {
                return approvalDecider;
            }
        }
        throw new IllegalArgumentException();
    }
}
