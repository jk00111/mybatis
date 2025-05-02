package com.example.mybatis.generalTest.receiptApproval.dto;

import com.example.mybatis.common.entity.User;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReceiptEscalateDto {

    private ReceiptId receiptId;
    private User requester;
    private List<User> line;

}
