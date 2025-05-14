package com.example.mybatis.common.approval.entity;

import com.example.mybatis.common.approval.approvalLine.ApprovalLine;
import com.example.mybatis.common.approval.approvalLine.OrderlessApprovalLine;
import com.example.mybatis.common.approval.user.ApprovalUser;
import com.example.mybatis.common.approval.user.ApprovalDecider;
import com.example.mybatis.common.approval.vo.ApprovalEscalateInfo;
import com.example.mybatis.common.approval.vo.ApprovalId;
import com.example.mybatis.common.approval.vo.ApprovalSubmit;
import com.example.mybatis.common.approval.user.ApprovalRequester;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApprovalEntity {

    private ApprovalId id;
    private ApprovalUser requester;
    private ApprovalLine approvalLine;

    public ApprovalEntity(ApprovalId id, ApprovalRequester requester, ApprovalLine approvalLine) {
        this.id = id;
        this.requester = requester;
        this.approvalLine = approvalLine;
    }

    public ApprovalEntity() {

    }

    public void approve(ApprovalSubmit submit) {
        ApprovalDecider approver = approvalLine.findApprovalDecider(submit.submitUser());
        approver.approve();
    }

    public void reject(ApprovalSubmit submit) {
        ApprovalDecider approver = approvalLine.findApprovalDecider(submit.submitUser());
        approver.reject();
    }

    public void cancel() {
    }

    public ApprovalId id() {
        return id;
    }

    public boolean isFinish() {
        return approvalLine.isFinish();
    }

    public static ApprovalEntity from(ApprovalEscalateInfo info) {
        ApprovalEntity entity = new ApprovalEntity();
        List<ApprovalDecider> line = info.getLine();
        entity.setRequester(info.getRequester());
        entity.setApprovalLine(new OrderlessApprovalLine(line));
        return entity;
    }

    public static ApprovalEntity of(ApprovalEntityDto entityDto, List<ApprovalDecider> line) {
        return  new ApprovalEntity(
                    new ApprovalId(entityDto.getId()),
                    new ApprovalRequester(
                        entityDto.getRequesterId(),
                        entityDto.getRequesterName(),
                        entityDto.getRequesterRole(),
                        entityDto.getRequesterAction()
                            ),
                    new OrderlessApprovalLine(line)
                );
    }
}
