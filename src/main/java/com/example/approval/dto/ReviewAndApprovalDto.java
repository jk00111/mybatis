package com.example.approval.dto;

public class ReviewAndApprovalDto {

    private ApprovalDto approvalDto;

    private ReviewDto reviewDto;


    public ApprovalDto forApproval() {
        return this.approvalDto;
    }

    public ReviewDto forReview() {
        return this.reviewDto;
    }
}
