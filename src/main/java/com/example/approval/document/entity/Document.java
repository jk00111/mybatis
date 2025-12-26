package com.example.approval.document.entity;

import com.example.approval.document.dto.DocumentDto;
import com.example.approval.vo.ApprovalUser;
import lombok.Getter;

public class Document {

    @Getter
    private long id;
    private ApprovalUser requester;
    private long approvalId;
    private long reviewId;


    public long approvalId() {
        return this.approvalId;
    }

    public long reviewId() {
        return this.reviewId;
    }

    public void update(DocumentDto dto) {

    }

    public void cancel(DocumentDto dto) {
        if (!validateRequester(dto.getRequester())) {
            throw new IllegalArgumentException();
        }
    }

    private boolean validateRequester(ApprovalUser user) {
        return this.requester.equals(user);
    }
}
