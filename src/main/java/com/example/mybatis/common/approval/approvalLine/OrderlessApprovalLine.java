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
        if (isRejected()) {
            return true;
        }

        for (ApprovalDecider decider : line) {
            if (ApprovalAction.NONE.equals(decider.action())) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isRejected() {
        for (ApprovalDecider decider : line) {
            if (ApprovalAction.REJECT.equals(decider.action())) {
                return true;
            }
        }

        return false;
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
    public List<ApprovalDecider> getAll() {
        return line;
    }

    @Override
    public ApprovalDecider findDeciderInLine(ApprovalDecider user) {
        for (ApprovalDecider approvalDecider : line) {
            if (approvalDecider.equals(user)) {
                return approvalDecider;
            }
        }
        throw new IllegalArgumentException("not include line");
    }
}
