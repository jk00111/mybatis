package com.example.mybatis.generalTest.receipt.controller;

import com.example.mybatis.common.config.model.CommonResponse;
import com.example.mybatis.generalTest.receipt.dto.ReceiptCondition;
import com.example.mybatis.generalTest.receipt.dto.ReceiptContentsDto;
import com.example.mybatis.generalTest.receipt.dto.ReceiptResponseDto;
import com.example.mybatis.generalTest.receipt.service.ReceiptQueryService;
import com.example.mybatis.generalTest.receipt.service.ReceiptService;
import com.example.mybatis.generalTest.receipt.vo.ReceiptContents;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test/general/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;
    private final ReceiptQueryService queryService;

    @PostMapping
    public ResponseEntity<CommonResponse> write(@RequestBody ReceiptContentsDto dto) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("not supported direct-create");
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> update(@RequestBody ReceiptContentsDto dto, @PathVariable Integer id) {
        ReceiptId receiptId = new ReceiptId(id);
        ReceiptContents contents = dto.toContents();
        receiptService.update(receiptId, contents);
        return ResponseEntity.ok(new CommonResponse());
    }

    @PostMapping("/{id}/submit")
    public ResponseEntity<CommonResponse> submit(@PathVariable Integer id) {
        ReceiptId receiptId = new ReceiptId(id);
        receiptService.submit(receiptId);
        return ResponseEntity.ok(new CommonResponse());
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<CommonResponse> cancel(@PathVariable Integer id) {
        ReceiptId receiptId = new ReceiptId(id);
        receiptService.cancel(receiptId);
        return ResponseEntity.ok(new CommonResponse());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptResponseDto> findOne(@PathVariable Integer id) {
        return ResponseEntity.ok(queryService.findOne(new ReceiptId(id)));
    }

    @GetMapping
    public ResponseEntity<List<ReceiptResponseDto>> findAll(ReceiptCondition condition) {
        return ResponseEntity.ok(queryService.findAll(condition));
    }
}
