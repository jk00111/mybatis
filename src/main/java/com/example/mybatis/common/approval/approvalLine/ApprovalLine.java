package com.example.mybatis.common.approval.approvalLine;

import com.example.mybatis.common.approval.vo.ApprovalSubmit;
import com.example.mybatis.common.approval.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ApprovalLine implements ApprovalUnit, Manageable {

    private final List<ApprovalUnit> steps;

    @Override
    public void approve(ApprovalUser user) {
        steps.forEach(step -> step.approve(user));
    }

    @Override
    public void reject(ApprovalUser user) {
        steps.forEach(step -> step.reject(user));
    }

    @Override
    public List<ApprovalUnit> getLeaf() {
        return steps;
    }

    @Override
    public void add(ApprovalUnit unit) {
        steps.add(unit);
    }

    @Override
    public void remove(ApprovalUnit unit) {
        steps.remove(unit);
    }

    @Override
    public ApprovalUnit get(ApprovalUser user) {
        for (ApprovalUnit step : steps) {
            if (step.contains(user)) {
                return step;
            }
        }

        throw new IllegalArgumentException("not included user");
    }

    @Override
    public boolean contains(ApprovalUser user) {
        for (ApprovalUnit step : steps) {
            if (step.contains(user)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isFinish() {
        for (ApprovalUnit step : steps) {
            if (!step.isFinish()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isUpdated() {
        for (ApprovalUnit step : steps) {
            if (step.isUpdated()) {
                return true;
            }
        }
        return false;
    }
}
