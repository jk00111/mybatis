package com.example.approval.line.entity;

import com.example.approval.enums.StepStatus;
import com.example.approval.line.policy.ApprovalPolicy;
import com.example.approval.line.policy.DefaultApprovalPolicy;
import com.example.approval.vo.ApprovalUser;

import java.util.Iterator;
import java.util.List;


/**
 * 승인 방식을 결정하는 것은 라인의 책임?
 * */
public class ApprovalLine implements Iterable<ApprovalStep> {

    private final long approvalId;
    private final List<ApprovalStep> steps;
    private final ApprovalPolicy policy;
    private int current;

    public ApprovalLine(long approvalId, List<ApprovalStep> steps) {
        this(approvalId, steps, new DefaultApprovalPolicy());
    }

    public ApprovalLine(long approvalId, List<ApprovalStep> steps, ApprovalPolicy policy) {
        this.approvalId = approvalId;
        this.steps = steps;
        this.policy = policy;
        this.current = current();
    }

    @Override
    public Iterator<ApprovalStep> iterator() {
        return steps.iterator();
    }

    public ApprovalStep getCurrent() {
        return steps.get(this.current);
    }

    public ApprovalStep next() {
        if (!hasNext()) {
            throw new IndexOutOfBoundsException();
        }

        current++;
        return steps.get(current);
    }

    public boolean hasNext() {
        return this.current < steps.size() + 1;
    }

    public void approve(ApprovalUser approver) {
        policy.submit();
    }

    public boolean isApproved() {
        return steps.stream()
                .allMatch(v -> v.status().equals(StepStatus.APPROVED));
    }

    public boolean isRejected() {
        return steps.stream()
                .anyMatch(v -> v.status().equals(StepStatus.REJECTED));
    }

    private int current() {
        ApprovalStep current = steps.stream()
                .filter(v -> v.status().equals(StepStatus.WAITING))
                .findFirst()
                .orElseThrow();
        return steps.indexOf(current);
    }
}
