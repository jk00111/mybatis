package com.example.approval.line.entity;

import com.example.approval.enums.StepStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ReviewStepImpl implements ReviewStep {

    @EqualsAndHashCode.Include
    private final long id;

    @EqualsAndHashCode.Include
    private final long reviewerId;

    private StepStatus status;

    @Override
    public long id() {
        return this.id;
    }

    @Override
    public void proceed() {
        this.status = StepStatus.REVIEWED;
    }
    @Override
    public void reject() {
        this.status = StepStatus.REJECTED;
    }

    @Override
    public StepStatus status() {
        return this.status;
    }

    @Override
    public void waiting() {
        this.status = StepStatus.WAITING;
    }
}
