package com.example.approval.dto;

import com.example.approval.approval.dto.ApprovalDto;
import com.example.approval.document.dto.DocumentDto;
import com.example.approval.review.dto.ReviewDto;
import lombok.Getter;

@Getter
public class ReviewAndApprovalDto {

    private DocumentDto documentDto;

    private ApprovalDto approvalDto;

    private ReviewDto reviewDto;

}
