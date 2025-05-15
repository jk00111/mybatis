package com.example.mybatis.generalTest.receipt.entity;

import com.example.mybatis.common.approval.vo.ApprovalId;
import com.example.mybatis.generalTest.receipt.dto.ReceiptCreateDto;
import com.example.mybatis.generalTest.receipt.dto.ReceiptServiceDto;
import com.example.mybatis.generalTest.receipt.enums.ReceiptStatus;
import com.example.mybatis.generalTest.receipt.vo.Item;
import com.example.mybatis.generalTest.receipt.vo.ReceiptContents;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import com.example.mybatis.generalTest.receipt.vo.Receptionist;
import com.example.mybatis.generalTest.request.vo.RequestId;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
public class Receipt {

    private ReceiptId id;
    private RequestId requestId;
    private ReceiptContents contents;
    private ReceiptStatus status;
    private Receptionist receptionist;
    private List<Item> items;
    private ApprovalId approvalId;

    public void updateFrom(ReceiptServiceDto dto) {
        this.contents = dto.getContents();
        this.receptionist = dto.getReceptionist();
        this.items = dto.getItems();
    }

    public void cancel() {
        status = ReceiptStatus.CANCEL;
    }

    public void submit() {
        status = ReceiptStatus.SUBMIT;
    }

    public void escalate(ApprovalId approvalId) {
        this.approvalId = approvalId;
    }

    public static Receipt ofCreate(ReceiptCreateDto dto) {
        return Receipt.builder()
                .requestId(dto.getRequestId())
                .status(ReceiptStatus.INIT)
                .build();
    }
}
