package com.example.mybatis.generalTest.receipt.dto;

import com.example.mybatis.generalTest.receipt.vo.Item;
import com.example.mybatis.generalTest.receipt.vo.ReceiptContents;
import com.example.mybatis.generalTest.receipt.vo.Receptionist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptServiceDto {

    private ReceiptContents contents;
    private Receptionist receptionist;
    private List<Item> items;

    public static ReceiptServiceDto from(ReceiptRequestDto requestDto) {
        return new ReceiptServiceDto(toContents(requestDto), requestDto.getReceptionist(), requestDto.getItems());
    }

    private static ReceiptContents toContents(ReceiptRequestDto requestDto) {
        return ReceiptContents.builder()
                .title(requestDto.getTitle())
                .contentsDetail(requestDto.getContentsDetail())
                .receiptDate(requestDto.getReceiptDate())
                .build();
    }
}
