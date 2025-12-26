package com.example.approval;

import com.example.approval.approval.entity.Approval;
import com.example.approval.approval.service.ApprovalService;
import com.example.approval.document.service.DocumentService;
import com.example.approval.approval.dto.ApprovalDto;
import com.example.approval.document.dto.DocumentDto;
import com.example.approval.dto.ReviewAndApprovalDto;
import com.example.approval.review.dto.ReviewDto;
import com.example.approval.event.ApprovalEvent;
import com.example.approval.event.ApproveEvent;
import com.example.approval.event.RejectEvent;
import com.example.approval.event.ReviewEvent;
import com.example.approval.line.entity.ApprovalLine;
import com.example.approval.line.entity.ReviewLine;
import com.example.approval.line.entity.ReviewStep;
import com.example.approval.line.service.LineService;
import com.example.approval.review.entity.Review;
import com.example.approval.review.service.ReviewService;
import com.example.approval.vo.ApprovalResult;
import com.example.approval.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class ApprovalFlowService {

    private final DocumentService documentService;
    private final ApprovalService approvalService;
    private final ReviewService reviewService;
    private final LineService lineService;

    public ApprovalResult escalate(ReviewAndApprovalDto dto, ApprovalUser requester) {
        DocumentDto documentDto = dto.getDocumentDto();
        long documentId = documentService.write(documentDto.toEntity());
        escalateReview(dto.getReviewDto(), requester);
        escalateApproval(dto.getApprovalDto(), requester);
        return ApprovalResult.escalated(documentId);
    }

    public ApprovalResult escalate(ApprovalDto approvalDto, ApprovalUser requester) {
        DocumentDto documentDto = approvalDto.getDocumentDto();
        long documentId = documentService.write(documentDto.toEntity());
        escalateApproval(approvalDto, requester);
        return ApprovalResult.escalated(documentId);
    }

    public ApprovalResult escalate(ReviewDto reviewDto, ApprovalUser requester) {
        DocumentDto documentDto = reviewDto.getDocumentDto();
        long documentId = documentService.write(documentDto.toEntity());
        escalateReview(reviewDto, requester);
        return ApprovalResult.escalated(documentId);
    }

    public ApprovalResult cancel(DocumentDto documentDto, ApprovalUser requester) {
        documentService.cancel(documentDto);
        return ApprovalResult.canceled(documentDto.getId());
    }

    public ApprovalResult approve(long id, ApprovalUser user) {
        ApprovalLine line = new ApprovalLine(id, lineService.findByApproval(id));
        ApproveEvent event = lineService.approve(line, user);

        approvalService.approve(id, event);
        return new ApprovalResult(id, event.isApproved());
    }

    public ApprovalResult rejectApproval(long id, ApprovalUser user) {
        ApprovalLine line = new ApprovalLine(id, lineService.findByApproval(id));
        RejectEvent event = lineService.reject(line, user);

        approvalService.reject(id, event);
        return new ApprovalResult(id, event.isRejected());
    }

    public ApprovalResult review(long id, ApprovalUser user) {
        ReviewLine reviewLine = new ReviewLine(id, lineService.findByReview(id));
        ReviewEvent event = lineService.review(reviewLine, user);

        reviewService.review(id, event);
        return new ApprovalResult(id, event.isReviewed());
    }

    public ApprovalResult rejectReview(long id, ApprovalUser user) {
        ReviewLine reviewLine = new ReviewLine(id, lineService.findByReview(id));
        ApprovalEvent event = lineService.review(reviewLine, user);

        reviewService.reject(id, event);
        return new ApprovalResult(id, event.isRejected());
    }

    private void escalateApproval(ApprovalDto approvalDto, ApprovalUser requester) {
        Approval approval = Approval.escalate(approvalDto, requester);
        approvalService.escalate(approval);

        long id = approval.id();
        ApprovalLine approvalLine = new ApprovalLine(id, approvalDto.steps());
        lineService.create(approvalLine);
    }

    private void escalateReview(ReviewDto reviewDto, ApprovalUser requester) {
        Review review = Review.escalate(reviewDto, requester);
        reviewService.escalate(review);

        Set<ReviewStep> steps = reviewDto.steps();
        ReviewLine reviewLine = new ReviewLine(review.id(), steps);
        lineService.create(reviewLine);
    }
}
