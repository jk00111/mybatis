package com.example.mybatis.common.approval.approvalLine;

import com.example.mybatis.common.approval.enums.ApprovalDecision;
import com.example.mybatis.common.approval.enums.ApprovalRole;
import com.example.mybatis.common.approval.vo.ApprovalUnitId;
import com.example.mybatis.common.approval.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalStep implements ApprovalUnit {

    private ApprovalUnitId id;
    private ApprovalUser approvalUser;
    private ApprovalRole role;
    private ApprovalDecision decision = ApprovalDecision.NOT;
    private boolean updateFlag = false;

    public ApprovalStep(ApprovalUser approvalUser) {
        this.approvalUser = approvalUser;
    }


    @Override
    public void approve(ApprovalUser user) {
        if (contains(user)) {
            decision = role.next();
            updateFlag = true;
        }
    }

    @Override
    public void reject(ApprovalUser user) {
        if (contains(user)) {
            decision = role.prev();
            updateFlag = true;
        }
    }

    @Override
    public ApprovalUnit get(ApprovalUser user) {
        if (contains(user)) {
            return this;
        }

        throw new IllegalArgumentException("not included user");
    }

    @Override
    public boolean contains(ApprovalUser user) {
        return approvalUser.equals(user);
    }

    @Override
    public boolean isFinish() {
        return !decision.equals(ApprovalDecision.NOT);
    }

    @Override
    public boolean isUpdated() {
        return updateFlag;
    }
}
