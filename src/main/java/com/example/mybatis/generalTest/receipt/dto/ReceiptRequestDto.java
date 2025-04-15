package com.example.mybatis.generalTest.receipt.dto;

import com.example.mybatis.generalTest.receipt.vo.Item;
import com.example.mybatis.generalTest.receipt.vo.ReceiptContents;
import com.example.mybatis.generalTest.receipt.vo.Receptionist;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ReceiptRequestDto {

    private String title;
    private String contentsDetail;
    private LocalDate receiptDate;
    private Receptionist receptionist;

    private List<Item> items;

}
