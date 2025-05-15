package com.example.mybatis.generalTest.receipt.controller;

import com.example.mybatis.common.config.model.CommonResponse;
import com.example.mybatis.common.entity.User;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import com.example.mybatis.generalTest.receiptApproval.dto.ReceiptApprovalSubmitDto;
import com.example.mybatis.generalTest.receiptApproval.dto.ReceiptEscalateDto;
import com.example.mybatis.generalTest.receiptApproval.service.ReceiptApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test/general/receipts")
public class ReceiptApprovalController {

    private final ReceiptApprovalService approvalService;

    @PostMapping("/{id}/escalate")
    public ResponseEntity<CommonResponse> escalate(@RequestBody ReceiptEscalateDto dto, @PathVariable Integer id) {
        dto.setReceiptId(new ReceiptId(id));
        dto.setRequester(getSessionUser());
        approvalService.escalate(dto);
        return ResponseEntity.ok(new CommonResponse());
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<CommonResponse> approve(@PathVariable Integer id) {
        approvalService.approve(new ReceiptApprovalSubmitDto(new ReceiptId(id), getApprover()));
        return ResponseEntity.ok(new CommonResponse());
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<CommonResponse> reject(@PathVariable Integer id) {
        approvalService.reject(new ReceiptApprovalSubmitDto(new ReceiptId(id), getRejecter()));
        return ResponseEntity.ok(new CommonResponse());
    }

    private User getSessionUser() {
        return User.builder().id(1).name("kim").build();
    }

    private User getApprover() {
        return User.builder().id(21).name("lee").build();
    }

    private User getRejecter() {
        return User.builder().id(42).name("me").build();
    }
}
