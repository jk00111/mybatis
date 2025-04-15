package com.example.mybatis.generalTest.receipt.controller;

import com.example.mybatis.common.config.model.CommonResponse;
import com.example.mybatis.generalTest.receipt.dto.ReceiptCondition;
import com.example.mybatis.generalTest.receipt.dto.ReceiptRequestDto;
import com.example.mybatis.generalTest.receipt.dto.ReceiptResponseDto;
import com.example.mybatis.generalTest.receipt.dto.ReceiptServiceDto;
import com.example.mybatis.generalTest.receipt.service.ReceiptQueryService;
import com.example.mybatis.generalTest.receipt.service.ReceiptService;
import com.example.mybatis.generalTest.receipt.vo.Item;
import com.example.mybatis.generalTest.receipt.vo.ReceiptContents;
import com.example.mybatis.generalTest.receipt.vo.ReceiptId;
import com.example.mybatis.generalTest.receipt.vo.Receptionist;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test/general/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;
    private final ReceiptQueryService queryService;

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> update(@RequestBody ReceiptRequestDto dto, @PathVariable Integer id) {
        ReceiptId receiptId = new ReceiptId(id);
        receiptService.update(receiptId, ReceiptServiceDto.from(dto));
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
