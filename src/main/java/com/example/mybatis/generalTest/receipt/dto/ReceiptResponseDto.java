package com.example.mybatis.generalTest.receipt.dto;

import com.example.mybatis.generalTest.receipt.enums.ReceiptStatus;
import com.example.mybatis.generalTest.receipt.vo.Item;
import com.example.mybatis.generalTest.receipt.vo.Receptionist;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ReceiptResponseDto {

    private Integer id;
    private String title;
    private String contents;
    private Receptionist receptionist;
    private LocalDate receiptDate;
    private ReceiptStatus status;
    private List<Item> items;

}
