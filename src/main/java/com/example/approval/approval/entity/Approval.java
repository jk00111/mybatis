package com.example.approval.approval.entity;

import com.example.approval.dto.ApprovalDto;
import com.example.approval.dto.DraftDto;
import com.example.approval.vo.ApprovalUser;
import com.example.approval.enums.ApprovalStatus;
import com.example.approval.event.ApproveEvent;
import com.example.approval.event.CancelEvent;
import com.example.approval.event.RejectEvent;

public class Approval {

    private long id;
    private String contents;
    private ApprovalStatus status;
    private ApprovalUser requester;

    public static Approval escalate(ApprovalDto dto) {
        return new Approval();
    }

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
