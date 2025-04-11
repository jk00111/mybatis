package com.example.mybatis.generalTest.receipt.dto;

import com.example.mybatis.generalTest.receipt.enums.ReceiptStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReceiptCondition {

    private final ReceiptStatus INIT_STATUS = ReceiptStatus.INIT;

    private String title;
    private LocalDate receiptDate;
}
