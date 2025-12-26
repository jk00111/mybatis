package com.example.approval.document.dto;

import com.example.approval.document.entity.Document;
import com.example.approval.vo.ApprovalUser;
import lombok.Getter;

public class DocumentDto {

    @Getter
    private long id;

    @Getter
    private ApprovalUser requester;


    public Document toEntity() {
        return new Document();
    }
}
