package com.example.mybatis.generalTest.receiptApproval.service;

import com.example.mybatis.common.approval.service.Approval;
import com.example.mybatis.common.approval.vo.ApprovalEscalateInfo;
import com.example.mybatis.common.approval.vo.ApprovalSubmit;
import com.example.mybatis.generalTest.receiptApproval.dto.ReceiptApprovalSubmitDto;
import com.example.mybatis.generalTest.receiptApproval.dto.ReceiptEscalateDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ReceiptApprovalServiceImpl implements ReceiptApprovalService {

    private final Approval approval;

    @Override
    public void escalate(ReceiptEscalateDto dto) {
        approval.escalate(
                new ApprovalEscalateInfo.builder()
                        .identityValue(dto.getReceiptId())
                        .requester(dto.getRequester())
                        .line(dto.getLine())
                        .build());
    }

    @Override
    public void approve(ReceiptApprovalSubmitDto submitDto) {
        approval.approve(ApprovalSubmit.ofApprove(submitDto.getApprovalId(), submitDto.getDecider()));
    }

    @Override
    public void reject(ReceiptApprovalSubmitDto submitDto) {
        approval.approve(ApprovalSubmit.ofReject(submitDto.getApprovalId(), submitDto.getDecider()));
    }
}
