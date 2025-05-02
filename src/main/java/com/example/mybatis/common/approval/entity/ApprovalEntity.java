package com.example.mybatis.common.approval.entity;

import com.example.mybatis.common.approval.approvalLine.ApprovalLine;
import com.example.mybatis.common.approval.approvalLine.ApprovalUnit;
import com.example.mybatis.common.approval.vo.ApprovalEscalateInfo;
import com.example.mybatis.common.approval.vo.ApprovalId;
import com.example.mybatis.common.approval.vo.ApprovalSubmit;
import com.example.mybatis.common.approval.vo.ApprovalUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApprovalEntity {

    private ApprovalId id;
    private ApprovalUser requester;
    private ApprovalUnit approvalUnit;

    public static ApprovalEntity escalate(ApprovalEscalateInfo info) {
        ApprovalEntity entity = new ApprovalEntity();
        ApprovalUnit approvalLine = ApprovalUnit.makeLine(info.getLine());
        entity.setRequester(info.getRequester());
        entity.setApprovalUnit(approvalLine);
        return entity;
    }


    public void approve(ApprovalSubmit submit) {
        approvalUnit.approve(submit.submitUser());
    }

    public void reject(ApprovalSubmit submit) {
        approvalUnit.reject(submit.submitUser());
    }

    public ApprovalId id() {
        return id;
    }

    public boolean isFinish() {
        return approvalUnit.isFinish();
    }

    public static ApprovalEntity of(ApprovalEntityDto entityDto, List<ApprovalUnit> units) {
        ApprovalEntity entity = new ApprovalEntity();
        entity.setId(ApprovalId.of(entityDto.getId()));

        ApprovalLine approvalLine = new ApprovalLine(units);
        entity.setApprovalUnit(approvalLine);
        return entity;
    }
}
