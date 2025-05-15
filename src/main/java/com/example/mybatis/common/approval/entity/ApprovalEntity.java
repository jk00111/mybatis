package com.example.mybatis.common.approval.entity;

import com.example.mybatis.common.approval.approvalLine.ApprovalLine;
import com.example.mybatis.common.approval.approvalLine.OrderlessApprovalLine;
import com.example.mybatis.common.approval.user.ApprovalDecider;
import com.example.mybatis.common.approval.user.ApprovalRequester;
import com.example.mybatis.common.approval.user.SimpleApprovalRequester;
import com.example.mybatis.common.approval.vo.ApprovalEscalateInfo;
import com.example.mybatis.common.approval.vo.ApprovalId;
import com.example.mybatis.common.approval.vo.ApprovalSubmit;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApprovalEntity {

    private ApprovalId id;
    private ApprovalRequester requester;
    private ApprovalLine approvalLine;

    public ApprovalEntity(ApprovalId id, ApprovalRequester requester, ApprovalLine approvalLine) {
        this.id = id;
        this.requester = requester;
        this.approvalLine = approvalLine;
    }

    public ApprovalEntity(ApprovalRequester requester, ApprovalLine approvalLine) {
        this.requester = requester;
        this.approvalLine = approvalLine;
    }

    public void approve(ApprovalSubmit submit) {
        ApprovalDecider approver = approvalLine.findDeciderInLine(submit.submitUser());
        approver.approve();
    }

    public void reject(ApprovalSubmit submit) {
        ApprovalDecider approver = approvalLine.findDeciderInLine(submit.submitUser());
        approver.reject();
    }

    public void cancel() {
        requester.cancel();
    }

    public ApprovalId id() {
        return id;
    }

    public boolean isFinish() {
        return approvalLine.isFinish();
    }

    public static ApprovalEntity escalate(ApprovalEscalateInfo info) {
        List<ApprovalDecider> line = info.getLine();
        ApprovalRequester requester = info.getRequester();
        return new ApprovalEntity(requester, new OrderlessApprovalLine(line));
    }

    public static ApprovalEntity of(ApprovalEntityDto entityDto, List<ApprovalDecider> line) {
        return  new ApprovalEntity(
                    new ApprovalId(entityDto.getId()),
                    new SimpleApprovalRequester(
                        entityDto.getRequesterId(),
                        entityDto.getRequesterName(),
                        entityDto.getRequesterRole(),
                        entityDto.getRequesterAction()
                            ),
                    new OrderlessApprovalLine(line)
                );
    }
}
