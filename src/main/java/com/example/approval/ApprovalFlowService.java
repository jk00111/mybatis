package com.example.approval;

import com.example.approval.approval.entity.Approval;
import com.example.approval.approval.service.ApprovalService;
import com.example.approval.event.ApprovalEvent;
import com.example.approval.line.entity.ApprovalLine;
import com.example.approval.line.service.LineService;
import com.example.approval.vo.ApprovalResult;
import com.example.approval.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ApprovalFlowService {

    private final ApprovalService approvalService;
    private final LineService lineService;

    public ApprovalResult approve(long id, ApprovalUser user) {
        ApprovalLine line = new ApprovalLine(id, lineService.findByApproval(id));
        ApprovalEvent event = lineService.approve(line);
        Approval approval = approvalService.findOne(id);
        approvalService.approve(approval, event);

        return new ApprovalResult(id, event);
    }

    public ApprovalResult reject(long id, ApprovalUser user) {
        ApprovalLine line = new ApprovalLine(id, lineService.findByApproval(id));
        ApprovalEvent event = lineService.reject(line);

        Approval approval = approvalService.findOne(id);
        approvalService.reject(approval, event);

        return new ApprovalResult(id, event);
    }
}
