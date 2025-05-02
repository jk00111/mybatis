package com.example.mybatis.generalTest.receiptApproval.service;

import com.example.mybatis.generalTest.receiptApproval.dto.ReceiptApprovalSubmitDto;
import com.example.mybatis.generalTest.receiptApproval.dto.ReceiptEscalateDto;

public interface ReceiptApprovalService {

    void escalate(ReceiptEscalateDto dto);

    void approve(ReceiptApprovalSubmitDto submitDto);

    void reject(ReceiptApprovalSubmitDto submitDto);

}
