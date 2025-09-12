package com.example.approval.approval.entity;

import com.example.approval.vo.ApprovalUser;
import com.example.approval.enums.ApprovalStatus;
import com.example.approval.event.ApproveEvent;
import com.example.approval.event.CancelEvent;
import com.example.approval.event.RejectEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Approval {

    private final long id;
    private String contents;
    private ApprovalStatus status;
    private ApprovalUser requester;

    public long id() {
        return id;
    }

    public void approve(ApproveEvent event) {
        if (!event.isApproved()) {
            return;
        }
        this.status = ApprovalStatus.APPROVED;
    }

    public void reject(RejectEvent event) {
        if (!event.isRejected()) {
            return;
        }
        this.status = ApprovalStatus.REJECTED;
    }

    public void cancel(CancelEvent event) {
        if (!event.isCanceled()) {
            return;
        }

        this.status = ApprovalStatus.CANCELED;
    }
}
