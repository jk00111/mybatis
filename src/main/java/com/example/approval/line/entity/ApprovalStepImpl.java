package com.example.approval.line.entity;

import com.example.approval.vo.ApprovalUser;
import com.example.approval.enums.StepStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalStepImpl implements ApprovalStep {

    private final long id;
    private final ApprovalUser approver;
    private StepStatus status;


    @Override
    public long id() {
        return this.id;
    }

    @Override
    public void proceed(ApprovalUser user) {
        if (validate(user)) {
            return;
        }

        status = StepStatus.APPROVED;
    }

    @Override
    public void reject(ApprovalUser user) {
        if (validate(user)) {
            return;
        }

        status = StepStatus.REJECTED;
    }

    @Override
    public void waiting() {
        status = StepStatus.WAITING;
    }

    @Override
    public void pass() {
        status = StepStatus.PASSED;
    }

    @Override
    public StepStatus status() {
        return status;
    }

    private boolean validate(ApprovalUser user) {
        return !this.approver.equals(user);
    }
}
