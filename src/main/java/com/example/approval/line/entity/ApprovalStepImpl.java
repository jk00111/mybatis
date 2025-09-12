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
    public void proceed() {
        status = StepStatus.APPROVED;
    }

    @Override
    public void reject() {
        status = StepStatus.REJECTED;
    }

    @Override
    public void waiting() {
        status = StepStatus.WAITING;
    }

    @Override
    public StepStatus status() {
        return status;
    }
}
