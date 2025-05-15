package com.example.mybatis.generalTest.receiptApproval.dto;

import com.example.mybatis.common.entity.User;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptApprovalSubmitDto {

    private ReceiptId receiptId;
    private User decider;

    public ReceiptApprovalSubmitDto(ReceiptId receiptId, User decider) {
        this.receiptId = receiptId;
        this.decider = decider;
    }
}
