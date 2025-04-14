package com.example.mybatis.generalTest.receipt.entity;

import com.example.mybatis.generalTest.receipt.enums.ReceiptStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReceiptEntityDto {

    private Integer id;
    private Integer requestId;
    private String title;
    private String contents;
    private LocalDate receiptDate;
    private ReceiptStatus status;
    private Integer receptionistId;


    public static ReceiptEntityDto fromCreate(Receipt receipt) {
        ReceiptEntityDto entityDto = new ReceiptEntityDto();
        entityDto.setRequestId(receipt.getRequestId().get());
        entityDto.setStatus(receipt.getStatus());
        return entityDto;
    }

    public static ReceiptEntityDto fromUpdate(Receipt receipt) {
        ReceiptEntityDto entityDto = new ReceiptEntityDto();
        entityDto.setId(receipt.getId().get());
        entityDto.setRequestId(receipt.getRequestId().get());
        entityDto.setTitle(receipt.getContents().getTitle());
        entityDto.setContents(receipt.getContents().getTitle());
        entityDto.setReceiptDate(receipt.getContents().getReceiptDate());
        entityDto.setStatus(receipt.getStatus());
        entityDto.setReceptionistId(receipt.getReceptionist().id());
        return entityDto;
    }
}
