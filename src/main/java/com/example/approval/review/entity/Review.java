package com.example.approval.review.entity;

import com.example.approval.dto.ReviewDto;
import com.example.approval.enums.ReviewStatus;
import com.example.approval.event.CancelEvent;
import com.example.approval.event.RejectEvent;
import com.example.approval.event.ReviewEvent;
import com.example.approval.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Review {

    private long id;
    private String contents;
    private ReviewStatus status;
    private ApprovalUser requester;

    public long id() {
        return id;
    }

    public static Review escalate(ReviewDto reviewDto) {
        return new Review();
    }

    public void review(ReviewEvent event) {
        this.status = ReviewStatus.REVIEWED;
    }

    public void reject(RejectEvent event) {
        if (!event.isRejected()) {
            return;
        }
        this.status = ReviewStatus.REJECTED;
    }

    public void cancel(CancelEvent event) {
        if (!event.isCanceled()) {
            return;
        }

        this.status = ReviewStatus.CANCELED;
    }
}
