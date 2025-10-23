package com.example.approval.dto;

import com.example.approval.approval.entity.Approval;

public class ReviewAndApprovalDto {

    private ApprovalDto approvalDto;

    private ReviewDto reviewDto;


    public ApprovalDto forApproval() {
        return this.approvalDto;
    }

    public ReviewDto forReview() {
        return this.reviewDto;
    }

    public Approval toEntity() {
        return new Approval();
    }
}
