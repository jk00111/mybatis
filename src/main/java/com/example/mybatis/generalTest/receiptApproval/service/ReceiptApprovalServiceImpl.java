package com.example.mybatis.generalTest.receiptApproval.service;

import com.example.mybatis.common.approval.service.Approval;
import com.example.mybatis.common.approval.vo.ApprovalEscalateInfo;
import com.example.mybatis.common.approval.vo.ApprovalResult;
import com.example.mybatis.common.approval.vo.ApprovalSubmit;
import com.example.mybatis.generalTest.receipt.entity.Receipt;
import com.example.mybatis.generalTest.receipt.repository.ReceiptRepository;
import com.example.mybatis.generalTest.receiptApproval.dto.ReceiptApprovalSubmitDto;
import com.example.mybatis.generalTest.receiptApproval.dto.ReceiptEscalateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReceiptApprovalServiceImpl implements ReceiptApprovalService {

    private final Approval approval;
    private final ReceiptRepository receiptRepository;

    @Override
    public void escalate(ReceiptEscalateDto dto) {
        ApprovalResult result = approval.escalate(
                new ApprovalEscalateInfo.Builder()
                        .identityValue(dto.getReceiptId())
                        .requester(dto.getRequester())
                        .line(dto.getLine())
                        .build());

        Receipt receipt = receiptRepository.findOne(dto.getReceiptId());
        receipt.escalate(result.id());
        receiptRepository.update(receipt);
    }

    @Override
    public void approve(ReceiptApprovalSubmitDto submitDto) {
        Receipt receipt = receiptRepository.findOne(submitDto.getReceiptId());
        approval.approve(ApprovalSubmit.of(receipt.getApprovalId(), submitDto.getDecider()));
    }

    @Override
    public void reject(ReceiptApprovalSubmitDto submitDto) {
        Receipt receipt = receiptRepository.findOne(submitDto.getReceiptId());
        approval.reject(ApprovalSubmit.of(receipt.getApprovalId(), submitDto.getDecider()));
    }
}
