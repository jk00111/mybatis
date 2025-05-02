package com.example.mybatis.generalTest.receiptApproval.dto;

import com.example.mybatis.common.approval.vo.ApprovalId;
import com.example.mybatis.common.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReceiptApprovalSubmitDto {

    private ApprovalId approvalId;
    private User decider;

}
