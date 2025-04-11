package com.example.mybatis.generalTest.receipt.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class ReceiptContents {

    private final String title;
    private final String contentsDetail;
    private LocalDate receiptDate;
    private Receptionist receptionist;

    private List<Item> items;
}
