package com.example.approval.line.entity;

import com.example.approval.enums.StepStatus;
import lombok.RequiredArgsConstructor;

import java.util.Iterator;
import java.util.List;

public class ApprovalLine implements Iterable<ApprovalStep> {

    private final long approvalId;
    private final List<ApprovalStep> steps;
    private int current;

    public ApprovalLine(long approvalId, List<ApprovalStep> steps) {
        this.approvalId = approvalId;
        this.steps = steps;
        this.current = current();
    }

    @Override
    public Iterator<ApprovalStep> iterator() {
        return steps.iterator();
    }

    public ApprovalStep getCurrent() {
        return steps.get(current());
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
