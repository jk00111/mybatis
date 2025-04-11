package com.example.mybatis.generalTest.receipt.entity;

import com.example.mybatis.generalTest.receipt.dto.ReceiptCreateDto;
import com.example.mybatis.generalTest.receipt.enums.ReceiptStatus;
import com.example.mybatis.generalTest.receipt.vo.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
public class Receipt {

    private Integer id;
    private Integer requestId;
    private String title;
    private String contents;
    private LocalDate receiptDate;
    private ReceiptStatus status;
    private Integer receptionistId;

    private List<Item> items;

    public Receipt(Integer id, Integer requestId, String title, String contents, LocalDate receiptDate, ReceiptStatus status, Integer receptionistId, List<Item> items) {
        this.id = id;
        this.requestId = requestId;
        this.title = title;
        this.contents = contents;
        this.receiptDate = receiptDate;
        this.status = status;
        this.receptionistId = receptionistId;
        this.items = items;
    }

    public Receipt(Integer id, Integer requestId, String title, String contents, LocalDate receiptDate, ReceiptStatus status, Integer receptionistId) {
        this.id = id;
        this.requestId = requestId;
        this.title = title;
        this.contents = contents;
        this.receiptDate = receiptDate;
        this.status = status;
        this.receptionistId = receptionistId;
    }

    public ReceiptId id() {
        return new ReceiptId(id);
    }

    public ReceiptContents contents() {
        return ReceiptContents.builder()
                .title(title)
                .receiptDate(receiptDate)
                .receptionist(Receptionist.ofId(receptionistId))
                .contentsDetail(contents)
                .build();
    }

    public void updateContents(ReceiptContents contents) {
        this.title = contents.getTitle();
        this.contents = contents.getContentsDetail();
        this.items = contents.getItems();
        this.receiptDate = contents.getReceiptDate();
    }

    public void cancel() {
        status = ReceiptStatus.CANCEL;
    }

    public void submit() {
        status = ReceiptStatus.SUBMIT;
    }

    public static Receipt ofCreate(ReceiptCreateDto dto) {
        return Receipt.builder()
                .requestId(dto.getRequestId().get())
                .status(ReceiptStatus.INIT)
                .build();
    }
}
