package com.example.approval;

import com.example.approval.approval.entity.Approval;
import com.example.approval.approval.service.ApprovalService;
import com.example.approval.draft.DraftService;
import com.example.approval.dto.ApprovalDto;
import com.example.approval.dto.DraftDto;
import com.example.approval.dto.ReviewDto;
import com.example.approval.event.ApproveEvent;
import com.example.approval.event.RejectEvent;
import com.example.approval.event.ReviewEvent;
import com.example.approval.line.entity.ApprovalLine;
import com.example.approval.line.entity.ProcessStep;
import com.example.approval.line.entity.ReviewLine;
import com.example.approval.line.service.LineService;
import com.example.approval.review.entity.Review;
import com.example.approval.review.service.ReviewService;
import com.example.approval.vo.ApprovalResult;
import com.example.approval.vo.ApprovalUser;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class ApprovalFlowService {

    private final DraftService draftService;
    private final ApprovalService approvalService;
    private final ReviewService reviewService;
    private final LineService lineService;

    public ApprovalResult escalate(DraftDto dto, ApprovalUser requester) {
        long draftId = draftService.escalate(dto);
        escalateApproval(dto.forApproval(), requester);

        if (hasReview(dto)) {
            escalateReview(dto.forReview(), requester);
        }

        return ApprovalResult.escalated(draftId);
    }

    public ApprovalResult approve(long id, ApprovalUser user) {
        ApprovalLine line = new ApprovalLine(id, lineService.findByApproval(id));
        ApproveEvent event = lineService.approve(line);

        Approval approval = approvalService.findOne(id);
        approvalService.approve(approval, event);

        return new ApprovalResult(id, event.isApproved());
    }

    public ApprovalResult reject(long id, ApprovalUser user) {
        ApprovalLine line = new ApprovalLine(id, lineService.findByApproval(id));
        RejectEvent event = lineService.reject(line);

        Approval approval = approvalService.findOne(id);
        approvalService.reject(approval, event);

        return new ApprovalResult(id, event.isRejected());
    }

    public ApprovalResult review(long id, ApprovalUser user) {
        ReviewLine reviewLine = new ReviewLine(id, lineService.findByReview(id));
        ReviewEvent event = lineService.review(reviewLine, user);
        Review review = reviewService.findOne(id);
        reviewService.review(review, event);

        return new ApprovalResult(id, event.isReviewed());
    }

    private boolean hasReview(DraftDto dto) {
        return dto.forReview() != null;
    }

    private void escalateApproval(ApprovalDto approvalDto, ApprovalUser requester) {
        Approval approval = Approval.escalate(approvalDto);
        approvalService.escalate(approval);

        long id = approval.id();
        ApprovalLine approvalLine = new ApprovalLine(id, approvalDto.steps());
        lineService.create(approvalLine);
    }

    private void escalateReview(ReviewDto reviewDto, ApprovalUser requester) {
        Review review = Review.escalate(reviewDto);
        reviewService.escalate(review);

        Set<ProcessStep> steps = reviewDto.steps();
        ReviewLine reviewLine = new ReviewLine(review.id(), steps);
        lineService.create(reviewLine);
    }
}
